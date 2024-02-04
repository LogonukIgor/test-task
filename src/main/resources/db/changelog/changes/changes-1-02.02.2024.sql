-- Создание схемы task
CREATE SCHEMA IF NOT EXISTS task;

-- Создание таблицы user
CREATE TABLE IF NOT EXISTS task.user
(
    id                SERIAL PRIMARY KEY,
    name              VARCHAR(30)         NOT NULL,
    login             VARCHAR(100) UNIQUE NOT NULL,
    creation_date     TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    modification_date TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6)
);

-- Создание таблицы account
CREATE TABLE IF NOT EXISTS task.account
(
    uuid              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    code              VARCHAR(4) NOT NULL,
    currency          VARCHAR(3)       DEFAULT 'RUB',
    balance           DECIMAL(18, 2)   DEFAULT 0,
    user_id           BIGINT,
    creation_date     TIMESTAMP(6)     DEFAULT CURRENT_TIMESTAMP(6),
    modification_date TIMESTAMP(6)     DEFAULT CURRENT_TIMESTAMP(6),
    FOREIGN KEY (user_id) REFERENCES task.user (id)
);

-- Создание таблицы manager
CREATE TABLE IF NOT EXISTS task.manager
(
    id                SERIAL PRIMARY KEY,
    operation_type    VARCHAR(50)    NOT NULL,
    currency          VARCHAR(3)   DEFAULT 'RUB',
    source_account    UUID,
    target_account    UUID,
    quantity          DECIMAL(18, 2) NOT NULL,
    creation_date     TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    modification_date TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    FOREIGN KEY (source_account) REFERENCES task.account (uuid)
);