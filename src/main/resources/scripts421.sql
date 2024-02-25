--Ограничение на минимальный возраст студента:
ALTER TABLE Student
ADD CONSTRAINT CHK_Age CHECK (age >= 16);

--Ограничение на уникальность имен студентов (не равных нулю):
ALTER TABLE Student
ADD CONSTRAINT UNIQUE_Name_NonZero CHECK (name IS NOT NULL AND name <> '');

--Ограничение на уникальную пару "значение названия" - "цвет факультета":
ALTER TABLE Student
ADD CONSTRAINT UNIQUE_Name_FacultyColor UNIQUE (name, faculty_color);

--Задание значения возраста по умолчанию (20 лет) при создании студента без указания возраста:
ALTER TABLE Student
ADD CONSTRAINT DEFAULT_Age DEFAULT 20 FOR age;