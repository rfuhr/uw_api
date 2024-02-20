WITH RECURSIVE tree AS (
  SELECT 
    id,
    codigo,
    nome,
    id_superior,
    sintetica,
    ARRAY[id] AS path,
    1 AS level
  FROM 
    plano_classificacao_item
  WHERE 
    id_superior IS NULL

  UNION ALL

  SELECT 
    pci.id,
    pci.codigo,
    pci.nome,
    pci.id_superior,
    pci.sintetica,
    tree.path || pci.id,
    tree.level + 1
  FROM 
    plano_classificacao_item AS pci
  JOIN 
    tree ON pci.id_superior = tree.id
)
SELECT 
  jsonb_pretty(
    jsonb_agg(
      jsonb_build_object(
        'key', tree.path[array_length(tree.path, 1)],
        'data', jsonb_build_object(
          'id', tree.id,
          'codigo', tree.codigo,
          'nome', tree.nome,
          'sintetica', tree.sintetica
        ),
        'children', 
          CASE 
            WHEN EXISTS (SELECT 1 FROM plano_classificacao_item WHERE id_superior = tree.id) THEN (
              SELECT 
                jsonb_agg(
                  jsonb_build_object(
                    'key', child.path[array_length(child.path, 1)],
                    'data', jsonb_build_object(
                      'id', child.id,
                      'codigo', child.codigo,
                      'nome', child.nome,
                      'sintetica', child.sintetica
                    ),
                    'children', null
                  )
                )
              FROM 
                tree AS child
              WHERE 
                child.id_superior = tree.id
            )
            ELSE null
          END
      )
    )
  )
FROM 
  tree
WHERE 
  level = 1;