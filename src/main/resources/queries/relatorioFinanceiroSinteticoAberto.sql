with competencia as (
	select :dataCompetencia as data_competencia,
		   (date_trunc('month', :dataCompetencia::DATE) + interval '1 month' - interval '1 day') AS ultimo_dia_mes
)
SELECT 
	vw.parceiroid,
	vw.parceirolocalcpfcnpj,
	vw.parceironomerazaosocial,
	SUM(CASE 
            WHEN vw.parcelafinanceiradatavencimento <= competencia.data_competencia::DATE THEN valoressaldoparcela  
            ELSE 0 
        END) AS coluna1,
	SUM(CASE 
            WHEN competencia.data_competencia::DATE = competencia.ultimo_dia_mes::DATE AND parcelafinanceiradatavencimento > competencia.data_competencia::DATE AND parcelafinanceiradatavencimento <= date_trunc('month', competencia.data_competencia::DATE) + interval '1 month' - interval '1 day' THEN valoressaldoparcela
            WHEN competencia.data_competencia::DATE <> competencia.ultimo_dia_mes::DATE AND parcelafinanceiradatavencimento > competencia.data_competencia::DATE AND parcelafinanceiradatavencimento <= competencia.ultimo_dia_mes::DATE THEN valoressaldoparcela
            ELSE 0 
        END) AS coluna2,
	SUM(CASE 
            WHEN competencia.data_competencia::DATE = competencia.ultimo_dia_mes::DATE AND parcelafinanceiradatavencimento  > date_trunc('month', competencia.data_competencia::DATE) + interval '1 month' - interval '1 day' AND parcelafinanceiradatavencimento  <= date_trunc('month', competencia.data_competencia::DATE) + interval '2 months' - interval '1 day' THEN valoressaldoparcela 
            WHEN competencia.data_competencia::DATE <> competencia.ultimo_dia_mes::DATE AND parcelafinanceiradatavencimento  > competencia.ultimo_dia_mes::DATE AND parcelafinanceiradatavencimento  <= date_trunc('month', competencia.data_competencia::DATE) + interval '2 months' - interval '1 day' THEN valoressaldoparcela  
            ELSE 0 
        END) AS coluna3,
   SUM(CASE 
            WHEN competencia.data_competencia::DATE = competencia.ultimo_dia_mes::DATE AND parcelafinanceiradatavencimento  > date_trunc('month', competencia.data_competencia::DATE) + interval '2 months' - interval '1 day' AND parcelafinanceiradatavencimento  <= date_trunc('month', competencia.data_competencia::DATE) + interval '3 months' - interval '1 day' THEN valoressaldoparcela 
            WHEN competencia.data_competencia::DATE <> competencia.ultimo_dia_mes::DATE AND parcelafinanceiradatavencimento  > date_trunc('month', competencia.data_competencia::DATE) + interval '2 months' - interval '1 day' AND parcelafinanceiradatavencimento  <= date_trunc('month', competencia.data_competencia::DATE) + interval '3 months' - interval '1 day' THEN valoressaldoparcela  
            ELSE 0 
        END) AS coluna4,
	SUM(CASE 
            WHEN competencia.data_competencia::DATE = competencia.ultimo_dia_mes::DATE AND parcelafinanceiradatavencimento  > date_trunc('month', competencia.data_competencia::DATE) + interval '3 months' - interval '1 day' AND parcelafinanceiradatavencimento  <= date_trunc('month', competencia.data_competencia::DATE) + interval '4 months' - interval '1 day' THEN valoressaldoparcela 
            WHEN competencia.data_competencia::DATE <> competencia.ultimo_dia_mes::DATE AND parcelafinanceiradatavencimento  > date_trunc('month', competencia.data_competencia::DATE) + interval '3 months' - interval '1 day' AND parcelafinanceiradatavencimento  <= date_trunc('month', competencia.data_competencia::DATE) + interval '4 months' - interval '1 day' THEN valoressaldoparcela  
            ELSE 0 
        END) AS coluna5,
	SUM(CASE 
            WHEN competencia.data_competencia::DATE = competencia.ultimo_dia_mes::DATE AND parcelafinanceiradatavencimento  > date_trunc('month', competencia.data_competencia::DATE) + interval '4 months' - interval '1 day' AND parcelafinanceiradatavencimento  <= date_trunc('month', competencia.data_competencia::DATE) + interval '5 months' - interval '1 day' THEN valoressaldoparcela 
            WHEN competencia.data_competencia::DATE <> competencia.ultimo_dia_mes::DATE AND parcelafinanceiradatavencimento  > date_trunc('month', competencia.data_competencia::DATE) + interval '4 months' - interval '1 day' AND parcelafinanceiradatavencimento  <= date_trunc('month', competencia.data_competencia::DATE) + interval '5 months' - interval '1 day' THEN valoressaldoparcela  
            ELSE 0 
        END) AS coluna6,
	SUM(CASE 
            WHEN competencia.data_competencia::DATE = competencia.ultimo_dia_mes::DATE AND parcelafinanceiradatavencimento  > date_trunc('month', competencia.data_competencia::DATE) + interval '5 months' - interval '1 day' THEN valoressaldoparcela 
            WHEN competencia.data_competencia::DATE <> competencia.ultimo_dia_mes::DATE AND parcelafinanceiradatavencimento  > date_trunc('month', competencia.data_competencia::DATE) + interval '5 months' - interval '1 day' THEN valoressaldoparcela  
            ELSE 0 
        END) AS coluna7        
FROM 
    vw_fin_posicaotituloaberto vw
    join competencia competencia on 1 = 1
WHERE $P{sqlWhere}    
group by vw.parceiroid,
	vw.parceirolocalcpfcnpj,
	vw.parceironomerazaosocial
