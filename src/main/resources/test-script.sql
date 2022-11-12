-- Привет. Это приветственное письмо, чтобы познакомиться, я решил написать для сберчата
-- всем коллегам, с кем я вступаю в диалог в рамках проекта SberDocs.
-- Меня зовут Александр Григоренко, мне 36 лет и это моя первая работа в коммандной разработке
-- и в ай ти в целом (1-ая неделя прошла).По специальности я инженер-радиофизик,
-- но по специальности практически не работал. В общем, я программист-самоучка. Мне очень приятно
-- оказаться в вашей комманде и я надеюсь мне хватит сил, умений и знаний, чтобы
-- работать и приносить вклад в общее дело. Спасибо.



CREATE SCHEMA test;

CREATE TABLE test.json_table
(
    id       serial PRIMARY KEY,
    settings jsonb
);

DROP TABLE test.json_table;

INSERT INTO test.json_table (settings)
VALUES ('{
  "id": 10
}'),
       ('{
         "id": 12,
         "automaticCertification": {
           "id": 123
         }
       }'),
       ('{
         "id": 13,
         "automaticCertification": {
           "automatically": null
         }
       }'),
       ('{
         "id": 14,
         "automaticCertification": {
           "automatically": true
         }
       }'),
       ('{
         "id": 14,
         "automaticCertification": {
           "id": 123,
           "automatically": true
         }
       }'),
       ('{
         "id": 15,
         "automaticCertification": {
           "id": 123,
           "automatically": null
         }
       }');

SELECT *
FROM test.json_table
ORDER BY id;

UPDATE test.json_table
SET settings =
        CASE
            WHEN settings ? 'automaticCertification' = false
                THEN settings || '{"automaticCertification" : {"automatically" : false}}'
            WHEN ((settings -> 'automaticCertification') ->> 'automatically') IS NULL
                THEN jsonb_set(settings, '{automaticCertification, automatically}', 'false', true)
            ELSE settings
END;


