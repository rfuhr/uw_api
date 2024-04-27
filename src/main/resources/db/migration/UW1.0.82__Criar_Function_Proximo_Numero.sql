CREATE OR REPLACE FUNCTION public.obter_proximo_numero(
    IN p_empresa_filial_id int8,
    IN p_tipo_documento_id int8,
    IN p_serie int8
)
RETURNS int8
LANGUAGE plpgsql
AS $function$
DECLARE
    v_ultimo_numero int8;
BEGIN
    -- Verifica se existe um registro na tabela para os parâmetros informados
    SELECT ultimo_numero INTO v_ultimo_numero
    FROM public.controle_numeracao
    WHERE empresa_filial_id = p_empresa_filial_id
        AND tipo_documento_id = p_tipo_documento_id
        AND serie = p_serie;

    -- Se não existir, adiciona um novo registro com o último número 1 e retorna 1
    IF NOT FOUND THEN
        INSERT INTO public.controle_numeracao (empresa_filial_id, tipo_documento_id, serie, ultimo_numero, user_create, date_create)
        VALUES (p_empresa_filial_id, p_tipo_documento_id, p_serie, 1, 0, NOW());

        RETURN 1;
    ELSE
        -- Se existir, atualiza o número, retorna o próximo número e atualiza a tabela
        v_ultimo_numero := v_ultimo_numero + 1;

        UPDATE public.controle_numeracao
        SET ultimo_numero = v_ultimo_numero, user_update = 0, date_update = NOW()
        WHERE empresa_filial_id = p_empresa_filial_id
            AND tipo_documento_id = p_tipo_documento_id
            AND serie = p_serie;

        RETURN v_ultimo_numero;
    END IF;
END;
$function$;
