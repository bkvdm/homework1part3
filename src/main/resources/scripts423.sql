--Информация обо всех студентах школы Хогвартс (имя, возраст и факультет)
SELECT s.name, s.age, f.name as faculty_name
FROM Students s
JOIN Student_Faculties sf ON s.student_id = sf.student_id
JOIN Faculties f ON f.faculty_id = sf.faculty_id;

--Выбрать только тех студентов, которые имеют аватарки:
SELECT * FROM Students s
JOIN Avatar a ON a.student_id = s.student_id;