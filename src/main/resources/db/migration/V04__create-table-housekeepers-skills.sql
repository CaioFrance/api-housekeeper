CREATE TABLE IF NOT EXISTS housekeepers_skills (
    housekeeper_id BIGINT NOT NULL,
    skill_id BIGINT NOT NULL,
    FOREIGN KEY (housekeeper_id) REFERENCES housekeepers(id),
    FOREIGN KEY (skill_id) REFERENCES skills(id),
    PRIMARY KEY (housekeeper_id, skill_id),

    INDEX (housekeeper_id),
    INDEX (skill_id)
);