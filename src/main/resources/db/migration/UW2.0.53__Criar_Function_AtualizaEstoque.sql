CREATE OR REPLACE FUNCTION public.atualiza_saldo_estoque(pData_inicial character varying, pData_final character varying, pItem_id bigint, pEmpresa_filial_id bigint, 
                                                         pLocal_estoque_id bigint, pGrupo_contabil_id bigint, user_id bigint)
 RETURNS void
 LANGUAGE plpgsql
AS $function$
declare
	linhas RECORD;
    dia DATE;
    saldo_anterior numeric = 0;
    saldo_valor_anterior numeric = 0;
    saldo_dia numeric = 0;
    saldo_valor_dia numeric = 0;
    saldo_atual numeric = 0;
    saldo_valor_atual numeric = 0;
    custo_medio_atual numeric = 0;
begin
	for linhas in (select distinct m.empresa_filial_id, m.item_id , m.local_estoque_id, m.grupo_contabil_id, m.data
						from movimento_estoque m
						where m.data >= TO_DATE(pData_inicial, 'YYYY-MM-DD')
                        and   (0 = pEmpresa_filial_id or m.empresa_filial_id = pEmpresa_filial_id)
                        and   (0 = pGrupo_contabil_id or m.grupo_contabil_id = pGrupo_contabil_id)
						and   (0 = pItem_id or m.item_id = pItem_id)
						and   (0 = pLocal_estoque_id or m.local_estoque_id = pLocal_estoque_id)
						order by m.data) loop
		FOR dia IN select generate_series(TO_DATE(pData_inicial, 'YYYY-MM-DD'), TO_DATE(pData_final, 'YYYY-MM-DD'), '1 day')::DATE loop
			-- Obter o saldo anterior
			select coalesce(saldo_quantidade, 0) into saldo_anterior from saldo_estoque s
				where data <= dia - 1
				and   s.item_id = linhas.item_id
				and   s.local_estoque_id = linhas.local_estoque_id
                and   s.empresa_filial_id = linhas.empresa_filial_id
                and   s.grupo_contabil_id = linhas.grupo_contabil_id
				order by data desc
				limit 1;
                
			if saldo_anterior is null then
				saldo_anterior = 0;
			end if;                
             
            select coalesce(saldo_valor, 0) into saldo_valor_anterior from saldo_estoque s
				where data <= dia - 1
				and   s.item_id = linhas.item_id
				and   s.local_estoque_id = linhas.local_estoque_id
                and   s.empresa_filial_id = linhas.empresa_filial_id
                and   s.grupo_contabil_id = linhas.grupo_contabil_id
				order by data desc
				limit 1;
			
            if saldo_valor_anterior is null then
				saldo_valor_anterior = 0;
			end if;
		
			select coalesce(sum(case m.operacao_estoque_fisico when '1' then m.quantidade when '2' then m.quantidade  * -1 else 0 end), 0) into saldo_dia
			from movimento_estoque m
				where m.data = dia
				and   m.item_id = linhas.item_id
				and   m.local_estoque_id = linhas.local_estoque_id
				and   m.empresa_filial_id = linhas.empresa_filial_id
                and   m.grupo_contabil_id = linhas.grupo_contabil_id
				group by m.data
				order by m.data desc
				limit 1;
                
			if saldo_dia is null then
				saldo_dia = 0;
			end if;
            
  			select coalesce(sum(case m.operacao_estoque_financeiro when '1' then m.valor when '2' then m.valor  * -1 else 0 end), 0) into saldo_valor_dia
			from movimento_estoque m
				where m.data = dia
				and   m.item_id = linhas.item_id
				and   m.local_estoque_id = linhas.local_estoque_id
				and   m.empresa_filial_id = linhas.empresa_filial_id
                and   m.grupo_contabil_id = linhas.grupo_contabil_id
				group by m.data
				order by m.data desc
				limit 1;              

            if saldo_valor_dia is null then
				saldo_valor_dia = 0;
			end if;
		
			saldo_atual = saldo_anterior + saldo_dia;
            saldo_valor_atual = saldo_valor_anterior + saldo_valor_dia;
            if (saldo_valor_atual > 0 and saldo_atual > 0) then
                custo_medio_atual = saldo_valor_atual / saldo_atual;
            else
                custo_medio_atual = 0;
            end if;    
            
		
			IF not EXISTS (SELECT 8 FROM saldo_estoque sed 
			               WHERE sed.item_id = linhas.item_id 
			               and sed.data = dia 
                           and sed.empresa_filial_id = linhas.empresa_filial_id
                           and sed.grupo_contabil_id = linhas.grupo_contabil_id
			               and sed.local_estoque_id = linhas.local_estoque_id) then
			               
				INSERT INTO saldo_estoque (id, empresa_filial_id, grupo_contabil_id, item_id, data, local_estoque_id, saldo_quantidade, saldo_valor, custo_medio, user_create, user_update, date_create, date_update) 
				VALUES (nextval('seq_saldo_estoque'), linhas.empresa_filial_id, linhas.grupo_contabil_id, linhas.item_id, dia, linhas.local_estoque_id, saldo_atual, saldo_valor_atual, custo_medio_atual, user_id, user_id, current_date, current_date);
            else 
            	update saldo_estoque sed set
            		saldo_quantidade = saldo_atual,
                    saldo_valor = saldo_valor_atual,
                    custo_medio = custo_medio_atual
            	WHERE sed.item_id = linhas.item_id 
			               and sed.data = dia 
                           and sed.empresa_filial_id = linhas.empresa_filial_id
                           and sed.grupo_contabil_id = linhas.grupo_contabil_id
			               and sed.local_estoque_id = linhas.local_estoque_id;
            end if;
		
			saldo_anterior := saldo_atual;
            saldo_valor_anterior := saldo_valor_atual;
			
	    END loop;							

	end loop;	   
    
END;
$function$
;