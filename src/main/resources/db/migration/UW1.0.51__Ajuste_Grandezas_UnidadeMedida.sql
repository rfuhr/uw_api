ALTER TABLE public.unidade_medida ALTER COLUMN grandeza TYPE varchar(20) USING grandeza::varchar;

update unidade_medida set grandeza = 'Área' where grandeza = 'A';
update unidade_medida set grandeza = 'Comprimento' where grandeza = 'C';
update unidade_medida set grandeza = 'Embalagem' where grandeza = 'E';
update unidade_medida set grandeza = 'Peso' where grandeza = 'P';
update unidade_medida set grandeza = 'Volume' where grandeza = 'V';