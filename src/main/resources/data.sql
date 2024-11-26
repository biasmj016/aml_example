DO
$$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'similarity_algorithms') THEN
            CREATE TABLE similarity_algorithms (
                                              id SERIAL PRIMARY KEY,
                                              type VARCHAR(255) NOT NULL,
                                              weight DOUBLE PRECISION NOT NULL,
                                              create_at TIMESTAMP NOT NULL DEFAULT NOW()
            );

            INSERT INTO similarity_algorithms (type, weight, create_at) VALUES
                                                                       ('CosineSimilarity', 30, NOW()),
                                                                       ('DiceCoefficient', 20, NOW()),
                                                                       ('HammingDistance', 10, NOW()),
                                                                       ('JaccardSimilarity', 20, NOW()),
                                                                       ('JaroWinklerDistance', 20, NOW());
        END IF;
    END
$$;;