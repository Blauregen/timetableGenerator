
# timetableGenerator
A tool for schools, to automaticly generate a timetable for each class, teacher and rooms

# DOCUMENTATION
https://blauregen.github.io/timetableGenerator/ 

Download jar and execute on cmd with "java -jar <pathToJar> -c <pathToConfig>"

# Example Config:

> [general]
> noOfDaysPerWeek = 5
> noOfHoursPerDay = 10
> schoolName = HTL Leonding
> delimiter = ; //Delimiter for the input file
>
> [output]
> outputData = [CLASSES, TEACHERS, ROOMS]
> outputFormat = [EXCEL, CSV, CSV_MULTIPLE]
> outputPath = output/
>
> [constraints]
> constraints = [TeacherConstraint, DoubleHourConstraint, NoMoreThanThreeInRowConstraint, RoomConstraint]

> [input]
> subjects = subjects.csv
> weeklySubjects = weeklySubjects.csv
> classes = classes.csv
> teachers = teachers.csv
> rooms = rooms.csv


# Example CSV Input Files:

## Example Classes Input

> className;FormTeacher
> 3bhitm;Alexandra Wellisch
> 3ahitm;Robert Reder

## Example Rooms Input

> RoomName
> 252
> 251
> 300

## Example Subjects Input

> Subject;SubjectShortcut
> Maths;M
> Project Developement;ITP
> English;E

## Example Teachers Input

TeacherName;hoursPerDay;daysPerWeek;Subjects
Alexandra Wellisch;5;5;Englisch, Geschichte
Robert Reder;5;5;Informationstechnische Projekte, Softwareentwicklung
Martin Kerschner;3;7;Angewandte Mathematik


## Example WeeklySubjects Input

> Subject;timePerWeek;schoolClass
> Maths;3;3bhitm
> Maths;3;3ahitm
> Project Developement;3;3bhitm
> Project Developement;2;3ahitm

