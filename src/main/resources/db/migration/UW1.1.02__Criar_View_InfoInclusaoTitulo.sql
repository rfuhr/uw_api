create view vw_fin_infoinclusaotitulo as
select	t.id as titulo_id,
		mf.data_movimento as data_movimento_inclusao,
		mf.data_lancamento as data_lancamento_inclusao,
		mf.conta_id
from	titulo t 
		join parcela_financeiro pf on pf.titulo_id = t.id
		join movimento_financeiro mf on mf.parcela_id = pf.id
		join departamento d on d.id = t.departamento_id 
		join empresa_filial ef on ef.id = d.empresa_filial_id 
		join empresa e on e.id = ef.empresa_id ,
		config_sistema cs,
		config_sistema_financeiro csf
where	csf.config_sistema_id = cs.id
and     mf.operacao_movimento_financeiro_id = csf.oper_movfin_inc
and     mf.operacao_acessoria_financeira_id = csf.oper_acefin_princ
and     mf.seq_mvto = 1
and     mf.sub_seq_mvto = 1
and     pf.seq_parcela = 1;