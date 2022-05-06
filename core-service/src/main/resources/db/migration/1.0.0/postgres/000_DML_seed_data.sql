-- Insert teacher data
INSERT INTO gts_teacher (age, name, work_start_date, created_at)
VALUES (30, 'Teacher_1', '2010/01/01', '2022/01/01'),
       (31, 'Teacher_2', '2010/01/01', '2022/01/01'),
       (32, 'Teacher_3', '2010/01/01', '2022/01/01'),
       (33, 'Teacher_4', '2010/01/01', '2022/01/01'),
       (34, 'Teacher_5', '2010/01/01', '2022/01/01'),
       (35, 'Teacher_6', '2010/01/01', '2022/01/01'),
       (36, 'Teacher_7', '2010/01/01', '2022/01/01'),
       (37, 'Teacher_8', '2010/01/01', '2022/01/01'),
       (38, 'Teacher_9', '2010/01/01', '2022/01/01'),
       (39, 'Teacher_10', '2010/01/01', '2022/01/01'),
       (40, 'Teacher_11', '2010/01/01', '2022/01/01'),
       (41, 'Teacher_12', '2010/01/01', '2022/01/01'),
       (42, 'Teacher_13', '2010/01/01', '2022/01/01');

-- Insert classroom data
INSERT INTO gts_classroom (code, classroom_size, teacher_id, created_at)
VALUES ('HAN', 45, null, '2022/01/01'),
       ('DAC', 10, null, '2022/01/01'),
       ('MLM', 13, null, '2022/01/01'),
       ('KII', 40, null, '2022/01/01'),
       ('DEV', 22, null, '2022/01/01'),
       ('ASD', 20, null, '2022/01/01');

-- Insert student data
INSERT INTO gts_student (name, gender, date_of_birth, parent_name, group_type, classroom_id, created_at)
VALUES ('Student_1', 'male', '2010/01/01', 'Parent_1', 0, null, '2022/01/01'),
       ('Student_2', 'male', '2010/01/01', 'Parent_2', 0, null, '2022/01/01'),
       ('Student_3', 'female', '2010/01/01', 'Parent_3', 0, null, '2022/01/01'),
       ('Student_4', 'male', '2010/01/01', 'Parent_4', 0, null, '2022/01/01'),
       ('Student_5', 'male', '2010/01/01', 'Parent_5', 0, null, '2022/01/01'),
       ('Student_6', 'male', '2010/01/01', 'Parent_6', 0, null, '2022/01/01'),
       ('Student_7', 'female', '2010/01/01', 'Parent_7', 0, null, '2022/01/01'),
       ('Student_8', 'female', '2010/01/01', 'Parent_8', 0, null, '2022/01/01'),
       ('Student_9', 'male', '2010/01/01', 'Parent_9', 0, null, '2022/01/01'),
       ('Student_10', 'male', '2010/01/01', 'Parent_10', 0, null, '2022/01/01'),
       ('Student_11', 'female', '2010/01/01', 'Parent_11', 0, null, '2022/01/01'),
       ('Student_12', 'male', '2010/01/01', 'Parent_12', 0, null, '2022/01/01'),
       ('Student_13', 'male', '2010/01/01', 'Parent_13', 0, null, '2022/01/01'),
       ('Student_14', 'male', '2010/01/01', 'Parent_14', 0, null, '2022/01/01'),
       ('Student_15', 'male', '2010/01/01', 'Parent_15', 0, null, '2022/01/01');
