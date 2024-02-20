CREATE OR REPLACE FUNCTION public.proximo_codigo(p_nome_tabela text, p_nome_campo text)
 RETURNS integer
 LANGUAGE plpgsql
AS $function$
DECLARE
    proximo_codigo INTEGER;
   minimo_codigo INTEGER;
BEGIN
    -- Encontrar o próximo código disponível
    EXECUTE format('
        SELECT COALESCE(MIN(t1.%I + 1), 1)
        FROM %I t1
        LEFT JOIN %I t2 ON t1.%I + 1 = t2.%I
        WHERE t2.%I IS NULL', p_nome_campo, p_nome_tabela, p_nome_tabela, p_nome_campo, p_nome_campo, p_nome_campo) INTO proximo_codigo;

-- Encontrar minimo cadastrado
    EXECUTE format('
        SELECT MIN(t.%I)
        FROM %I t', p_nome_campo, p_nome_tabela) INTO minimo_codigo;
       
	IF minimo_codigo > 1 then
		return 1;
	end if;
	
    -- Se o próximo código for maior que o menor código encontrado,
    -- retorna o próximo código disponível
    IF proximo_codigo IS NOT NULL THEN
        RETURN proximo_codigo;
    END IF;

    -- Caso contrário, retorna 1
    RETURN 1;
END;
$function$
;
