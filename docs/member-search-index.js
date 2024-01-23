memberSearchIndex = [{
	'p': 'at.htl.timetableGenerator',
	'c': 'School',
	'l': 'addConstraint(Constraint)',
	'u': 'addConstraint(at.htl.timetableGenerator.Constraint)'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'SchoolClass',
	'l': 'addConstraint(Constraint)',
	'u': 'addConstraint(at.htl.timetableGenerator.Constraint)'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'App',
	'l': 'App()',
	'u': '%3Cinit%3E()'
}, {
	'p': 'at.htl.timetableGenerator.output',
	'c': 'TimetablePrinter',
	'l': 'calculateColumnWidth(Timetable)',
	'u': 'calculateColumnWidth(at.htl.timetableGenerator.Timetable)'
}, {
	'p': 'at.htl.timetableGenerator.constrains',
	'c': 'NonePlacedBeforeConstraint',
	'l': 'check(Timetable, TimeSlot, Subject, Set<Teacher>)',
	'u': 'check(at.htl.timetableGenerator.Timetable,at.htl.timetableGenerator.TimeSlot,at.htl.timetableGenerator.Subject,java.util.Set)'
}, {
	'p': 'at.htl.timetableGenerator.constrains',
	'c': 'DoubleHourConstraint',
	'l': 'check(Timetable, TimeSlot, Subject, Set<Teacher>)',
	'u': 'check(at.htl.timetableGenerator.Timetable,at.htl.timetableGenerator.TimeSlot,at.htl.timetableGenerator.Subject,java.util.Set)'
}, {
	'p': 'at.htl.timetableGenerator.constrains',
	'c': 'HasFreeHourConstraint',
	'l': 'check(Timetable, TimeSlot, Subject, Set<Teacher>)',
	'u': 'check(at.htl.timetableGenerator.Timetable,at.htl.timetableGenerator.TimeSlot,at.htl.timetableGenerator.Subject,java.util.Set)'
}, {
	'p': 'at.htl.timetableGenerator.constrains',
	'c': 'TeacherConstraint',
	'l': 'check(Timetable, TimeSlot, Subject, Set<Teacher>)',
	'u': 'check(at.htl.timetableGenerator.Timetable,at.htl.timetableGenerator.TimeSlot,at.htl.timetableGenerator.Subject,java.util.Set)'
}, {
	'p': 'at.htl.timetableGenerator.constrains',
	'c': 'NoMoreThanThreeInRowConstraint',
	'l': 'check(Timetable, TimeSlot, Subject, Set<Teacher>)',
	'u': 'check(at.htl.timetableGenerator.Timetable,at.htl.timetableGenerator.TimeSlot,at.htl.timetableGenerator.Subject,java.util.Set)'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'Constraint',
	'l': 'check(Timetable, TimeSlot, Subject, Set<Teacher>)',
	'u': 'check(at.htl.timetableGenerator.Timetable,at.htl.timetableGenerator.TimeSlot,at.htl.timetableGenerator.Subject,java.util.Set)'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'Timetable',
	'l': 'checkConstraints(TimeSlot, Subject)',
	'u': 'checkConstraints(at.htl.timetableGenerator.TimeSlot,at.htl.timetableGenerator.Subject)'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'SchoolClass',
	'l': 'checkConstraints(Timetable, TimeSlot, Subject, Set<Teacher>)',
	'u': 'checkConstraints(at.htl.timetableGenerator.Timetable,at.htl.timetableGenerator.TimeSlot,at.htl.timetableGenerator.Subject,java.util.Set)'
}, {'p': 'at.htl.timetableGenerator.output', 'c': 'ExportData', 'l': 'classes'}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'SchoolClass',
	'l': 'constraints'
}, {'p': 'at.htl.timetableGenerator', 'c': 'Timetable', 'l': 'constraints'}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'ConstraintUtils',
	'l': 'ConstraintUtils()',
	'u': '%3Cinit%3E()'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'Timetable',
	'l': 'contains(Subject)',
	'u': 'contains(at.htl.timetableGenerator.Subject)'
}, {'p': 'at.htl.timetableGenerator.output', 'c': 'ExportFormat', 'l': 'csv'}, {
	'p': 'at.htl.timetableGenerator.output',
	'c': 'CSVExporter',
	'l': 'CSVExporter()',
	'u': '%3Cinit%3E()'
}, {'p': 'at.htl.timetableGenerator', 'c': 'TimeSlot', 'l': 'day'}, {
	'p': 'at.htl.timetableGenerator.constrains',
	'c': 'DoubleHourConstraint',
	'l': 'DoubleHourConstraint()',
	'u': '%3Cinit%3E()'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'Lesson',
	'l': 'equals(Object)',
	'u': 'equals(java.lang.Object)'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'Subject',
	'l': 'equals(Object)',
	'u': 'equals(java.lang.Object)'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'TimeSlot',
	'l': 'equals(Object)',
	'u': 'equals(java.lang.Object)'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'WeeklySubjects',
	'l': 'equals(Object)',
	'u': 'equals(java.lang.Object)'
}, {
	'p': 'at.htl.timetableGenerator.output',
	'c': 'ExportFormat',
	'l': 'excel'
}, {
	'p': 'at.htl.timetableGenerator.output',
	'c': 'ExcelExporter',
	'l': 'ExcelExporter()',
	'u': '%3Cinit%3E()'
}, {
	'p': 'at.htl.timetableGenerator.output',
	'c': 'ExportData',
	'l': 'ExportData()',
	'u': '%3Cinit%3E()'
}, {
	'p': 'at.htl.timetableGenerator.exceptions',
	'c': 'ExportException',
	'l': 'ExportException(String)',
	'u': '%3Cinit%3E(java.lang.String)'
}, {
	'p': 'at.htl.timetableGenerator.exceptions',
	'c': 'ExportException',
	'l': 'ExportException(String, Throwable)',
	'u': '%3Cinit%3E(java.lang.String,java.lang.Throwable)'
}, {
	'p': 'at.htl.timetableGenerator.output',
	'c': 'ExportFormat',
	'l': 'ExportFormat()',
	'u': '%3Cinit%3E()'
}, {
	'p': 'at.htl.timetableGenerator.output',
	'c': 'CSVExporter',
	'l': 'exportTimetablesToMultipleFiles(Map<String, Timetable>, String)',
	'u': 'exportTimetablesToMultipleFiles(java.util.Map,java.lang.String)'
}, {
	'p': 'at.htl.timetableGenerator.output',
	'c': 'CSVExporter',
	'l': 'exportTimetablesToSingleFile(Map<String, Timetable>, String)',
	'u': 'exportTimetablesToSingleFile(java.util.Map,java.lang.String)'
}, {
	'p': 'at.htl.timetableGenerator.output',
	'c': 'CSVExporter',
	'l': 'exportTimetableToFile(Timetable, String, String)',
	'u': 'exportTimetableToFile(at.htl.timetableGenerator.Timetable,java.lang.String,java.lang.String)'
}, {
	'p': 'at.htl.timetableGenerator.output',
	'c': 'ExcelExporter',
	'l': 'exportToWorkbook(Map<String, Timetable>, String)',
	'u': 'exportToWorkbook(java.util.Map,java.lang.String)'
}, {'p': 'at.htl.timetableGenerator', 'c': 'Timetable', 'l': 'FREISTUNDE'}, {
	'p': 'at.htl.timetableGenerator.output',
	'c': 'CSVExporter',
	'l': 'generateCSV(Timetable, CSVPrinter, String)',
	'u': 'generateCSV(at.htl.timetableGenerator.Timetable,org.apache.commons.csv.CSVPrinter,java.lang.String)'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'SchoolClass',
	'l': 'generateTimetable(int, int, Set<Teacher>)',
	'u': 'generateTimetable(int,int,java.util.Set)'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'School',
	'l': 'generateTimetables(int, int)',
	'u': 'generateTimetables(int,int)'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'SchoolClass',
	'l': 'getBestAvailableSlot(Timetable, Subject, Set<Teacher>)',
	'u': 'getBestAvailableSlot(at.htl.timetableGenerator.Timetable,at.htl.timetableGenerator.Subject,java.util.Set)'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'ConstraintUtils',
	'l': 'getConstraintFromString(String)',
	'u': 'getConstraintFromString(java.lang.String)'
}, {'p': 'at.htl.timetableGenerator', 'c': 'TimeSlot', 'l': 'getDay()'}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'TimeSlot',
	'l': 'getHour()'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'Timetable',
	'l': 'getLesson(TimeSlot)',
	'u': 'getLesson(at.htl.timetableGenerator.TimeSlot)'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'Teacher',
	'l': 'getLesson(TimeSlot)',
	'u': 'getLesson(at.htl.timetableGenerator.TimeSlot)'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'Timetable',
	'l': 'getMaxNoOfHoursPerDay()'
}, {'p': 'at.htl.timetableGenerator', 'c': 'SchoolClass', 'l': 'getName()'}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'Teacher',
	'l': 'getName()'
}, {'p': 'at.htl.timetableGenerator', 'c': 'Timetable', 'l': 'getNoOfDayPerWeek()'}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'WeeklySubjects',
	'l': 'getNoPerWeek()'
}, {
	'p': 'at.htl.timetableGenerator.output',
	'c': 'TimetablePrinter',
	'l': 'getPadding(int, String)',
	'u': 'getPadding(int,java.lang.String)'
}, {'p': 'at.htl.timetableGenerator', 'c': 'Lesson', 'l': 'getSubject()'}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'WeeklySubjects',
	'l': 'getSubject()'
}, {'p': 'at.htl.timetableGenerator', 'c': 'Teacher', 'l': 'getSubjects()'}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'Lesson',
	'l': 'getTeacher()'
}, {'p': 'at.htl.timetableGenerator', 'c': 'Lesson', 'l': 'getTimeSlot()'}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'Teacher',
	'l': 'getTimetable()'
}, {'p': 'at.htl.timetableGenerator', 'c': 'Timetable', 'l': 'getTimetable()'}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'Timetable',
	'l': 'getTimetableAsArray()'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'SchoolClass',
	'l': 'getWeeklySubjects()'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'Timetable',
	'l': 'hasAvailableDoubleHourSpot(Subject)',
	'u': 'hasAvailableDoubleHourSpot(at.htl.timetableGenerator.Subject)'
}, {
	'p': 'at.htl.timetableGenerator.constrains',
	'c': 'HasFreeHourConstraint',
	'l': 'HasFreeHourConstraint()',
	'u': '%3Cinit%3E()'
}, {'p': 'at.htl.timetableGenerator', 'c': 'Lesson', 'l': 'hashCode()'}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'Subject',
	'l': 'hashCode()'
}, {'p': 'at.htl.timetableGenerator', 'c': 'TimeSlot', 'l': 'hashCode()'}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'WeeklySubjects',
	'l': 'hashCode()'
}, {'p': 'at.htl.timetableGenerator', 'c': 'TimeSlot', 'l': 'hour'}, {
	'p': 'at.htl.timetableGenerator.exceptions',
	'c': 'IncompatibleSubjectException',
	'l': 'IncompatibleSubjectException(String)',
	'u': '%3Cinit%3E(java.lang.String)'
}, {
	'p': 'at.htl.timetableGenerator.exceptions',
	'c': 'IncompatibleSubjectException',
	'l': 'IncompatibleSubjectException(String, Throwable)',
	'u': '%3Cinit%3E(java.lang.String,java.lang.Throwable)'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'Lesson',
	'l': 'Lesson(Subject, TimeSlot)',
	'u': '%3Cinit%3E(at.htl.timetableGenerator.Subject,at.htl.timetableGenerator.TimeSlot)'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'App',
	'l': 'main(String[])',
	'u': 'main(java.lang.String[])'
}, {'p': 'at.htl.timetableGenerator', 'c': 'Timetable', 'l': 'maxNoOfHoursPerDay'}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'Timetable',
	'l': 'MIN_NUMBER_OF_DAYS_PER_WEEK'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'Timetable',
	'l': 'MIN_NUMBER_OF_HOURS_PER_DAY'
}, {'p': 'at.htl.timetableGenerator.output', 'c': 'ExportFormat', 'l': 'multiple'}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'SchoolClass',
	'l': 'name'
}, {'p': 'at.htl.timetableGenerator', 'c': 'Subject', 'l': 'name'}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'Teacher',
	'l': 'name'
}, {'p': 'at.htl.timetableGenerator', 'c': 'Subject', 'l': 'name()'}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'TimeSlot',
	'l': 'nextDay()'
}, {'p': 'at.htl.timetableGenerator', 'c': 'TimeSlot', 'l': 'nextHour()'}, {
	'p': 'at.htl.timetableGenerator.constrains',
	'c': 'NoMoreThanThreeInRowConstraint',
	'l': 'NoMoreThanThreeInRowConstraint()',
	'u': '%3Cinit%3E()'
}, {
	'p': 'at.htl.timetableGenerator.constrains',
	'c': 'NonePlacedBeforeConstraint',
	'l': 'NonePlacedBeforeConstraint()',
	'u': '%3Cinit%3E()'
}, {'p': 'at.htl.timetableGenerator', 'c': 'Timetable', 'l': 'noOfDayPerWeek'}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'WeeklySubjects',
	'l': 'noPerWeek'
}, {'p': 'at.htl.timetableGenerator', 'c': 'Teacher', 'l': 'occupiedLessons'}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'TimeSlot',
	'l': 'prevDay()'
}, {'p': 'at.htl.timetableGenerator', 'c': 'TimeSlot', 'l': 'prevHour()'}, {
	'p': 'at.htl.timetableGenerator.output',
	'c': 'TimetablePrinter',
	'l': 'print(Timetable)',
	'u': 'print(at.htl.timetableGenerator.Timetable)'
}, {
	'p': 'at.htl.timetableGenerator.output',
	'c': 'TimetablePrinter',
	'l': 'printHeader(Timetable, int, StringBuilder)',
	'u': 'printHeader(at.htl.timetableGenerator.Timetable,int,java.lang.StringBuilder)'
}, {
	'p': 'at.htl.timetableGenerator.output',
	'c': 'CSVExporter',
	'l': 'printHeaders(CSVPrinter)',
	'u': 'printHeaders(org.apache.commons.csv.CSVPrinter)'
}, {
	'p': 'at.htl.timetableGenerator.output',
	'c': 'TimetablePrinter',
	'l': 'printRow(Timetable, int, int, StringBuilder)',
	'u': 'printRow(at.htl.timetableGenerator.Timetable,int,int,java.lang.StringBuilder)'
}, {
	'p': 'at.htl.timetableGenerator.output',
	'c': 'TimetablePrinter',
	'l': 'printSeparator(Timetable, int, StringBuilder)',
	'u': 'printSeparator(at.htl.timetableGenerator.Timetable,int,java.lang.StringBuilder)'
}, {
	'p': 'at.htl.timetableGenerator.output',
	'c': 'TimetablePrinter',
	'l': 'printThickSeparator(Timetable, int, StringBuilder)',
	'u': 'printThickSeparator(at.htl.timetableGenerator.Timetable,int,java.lang.StringBuilder)'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'School',
	'l': 'removeConstraint(Constraint)',
	'u': 'removeConstraint(at.htl.timetableGenerator.Constraint)'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'SchoolClass',
	'l': 'removeConstraint(Constraint)',
	'u': 'removeConstraint(at.htl.timetableGenerator.Constraint)'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'School',
	'l': 'School(Set<SchoolClass>, Set<Teacher>)',
	'u': '%3Cinit%3E(java.util.Set,java.util.Set)'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'SchoolClass',
	'l': 'SchoolClass(String, List<WeeklySubjects>)',
	'u': '%3Cinit%3E(java.lang.String,java.util.List)'
}, {'p': 'at.htl.timetableGenerator', 'c': 'School', 'l': 'schoolClasses'}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'TimeSlot',
	'l': 'setDay(DayOfWeek)',
	'u': 'setDay(java.time.DayOfWeek)'
}, {'p': 'at.htl.timetableGenerator', 'c': 'TimeSlot', 'l': 'setHour(int)'}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'Timetable',
	'l': 'setLesson(Lesson)',
	'u': 'setLesson(at.htl.timetableGenerator.Lesson)'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'Teacher',
	'l': 'setLesson(Lesson)',
	'u': 'setLesson(at.htl.timetableGenerator.Lesson)'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'Timetable',
	'l': 'setMaxNoOfHoursPerDay(int)'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'Timetable',
	'l': 'setNoOfDayPerWeek(int)'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'WeeklySubjects',
	'l': 'setNoPerWeek(int)'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'Teacher',
	'l': 'setOccupiedLessons(Timetable)',
	'u': 'setOccupiedLessons(at.htl.timetableGenerator.Timetable)'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'School',
	'l': 'setSchoolClasses(Set<SchoolClass>)',
	'u': 'setSchoolClasses(java.util.Set)'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'Lesson',
	'l': 'setSubject(Subject)',
	'u': 'setSubject(at.htl.timetableGenerator.Subject)'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'WeeklySubjects',
	'l': 'setSubject(Subject)',
	'u': 'setSubject(at.htl.timetableGenerator.Subject)'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'Teacher',
	'l': 'setSubjects(Set<Subject>)',
	'u': 'setSubjects(java.util.Set)'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'Lesson',
	'l': 'setTeacher(Teacher)',
	'u': 'setTeacher(at.htl.timetableGenerator.Teacher)'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'School',
	'l': 'setTeachers(Set<Teacher>)',
	'u': 'setTeachers(java.util.Set)'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'Lesson',
	'l': 'setTimeSlot(TimeSlot)',
	'u': 'setTimeSlot(at.htl.timetableGenerator.TimeSlot)'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'Timetable',
	'l': 'setTimetable(HashMap<TimeSlot, Lesson>)',
	'u': 'setTimetable(java.util.HashMap)'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'Timetable',
	'l': 'setTimetable(Subject)',
	'u': 'setTimetable(at.htl.timetableGenerator.Subject)'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'SchoolClass',
	'l': 'setWeeklySubjects(List<WeeklySubjects>)',
	'u': 'setWeeklySubjects(java.util.List)'
}, {'p': 'at.htl.timetableGenerator', 'c': 'Subject', 'l': 'shortName'}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'Subject',
	'l': 'shortName()'
}, {'p': 'at.htl.timetableGenerator', 'c': 'Lesson', 'l': 'subject'}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'WeeklySubjects',
	'l': 'subject'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'Subject',
	'l': 'Subject(String, String)',
	'u': '%3Cinit%3E(java.lang.String,java.lang.String)'
}, {'p': 'at.htl.timetableGenerator', 'c': 'Teacher', 'l': 'subjects'}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'Lesson',
	'l': 'teacher'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'Teacher',
	'l': 'Teacher(String, Set<Subject>, Timetable)',
	'u': '%3Cinit%3E(java.lang.String,java.util.Set,at.htl.timetableGenerator.Timetable)'
}, {
	'p': 'at.htl.timetableGenerator.constrains',
	'c': 'TeacherConstraint',
	'l': 'TeacherConstraint()',
	'u': '%3Cinit%3E()'
}, {'p': 'at.htl.timetableGenerator.output', 'c': 'ExportData', 'l': 'teachers'}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'School',
	'l': 'teachers'
}, {'p': 'at.htl.timetableGenerator', 'c': 'Lesson', 'l': 'timeSlot'}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'TimeSlot',
	'l': 'TimeSlot(DayOfWeek, int)',
	'u': '%3Cinit%3E(java.time.DayOfWeek,int)'
}, {'p': 'at.htl.timetableGenerator', 'c': 'Timetable', 'l': 'timetable'}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'Timetable',
	'l': 'Timetable(int, int)',
	'u': '%3Cinit%3E(int,int)'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'Timetable',
	'l': 'Timetable(int, int, Set<Constraint>)',
	'u': '%3Cinit%3E(int,int,java.util.Set)'
}, {
	'p': 'at.htl.timetableGenerator.output',
	'c': 'TimetablePrinter',
	'l': 'TimetablePrinter()',
	'u': '%3Cinit%3E()'
}, {'p': 'at.htl.timetableGenerator', 'c': 'Lesson', 'l': 'toString()'}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'Subject',
	'l': 'toString()'
}, {'p': 'at.htl.timetableGenerator', 'c': 'Teacher', 'l': 'toString()'}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'TimeSlot',
	'l': 'toString()'
}, {'p': 'at.htl.timetableGenerator', 'c': 'Timetable', 'l': 'toString()'}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'WeeklySubjects',
	'l': 'toString()'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'SchoolClass',
	'l': 'updateConstraints(Timetable, TimeSlot, Subject, Set<Teacher>)',
	'u': 'updateConstraints(at.htl.timetableGenerator.Timetable,at.htl.timetableGenerator.TimeSlot,at.htl.timetableGenerator.Subject,java.util.Set)'
}, {
	'p': 'at.htl.timetableGenerator.constrains',
	'c': 'TeacherConstraint',
	'l': 'updateOnSuccess(Timetable, TimeSlot, Subject, Set<Teacher>)',
	'u': 'updateOnSuccess(at.htl.timetableGenerator.Timetable,at.htl.timetableGenerator.TimeSlot,at.htl.timetableGenerator.Subject,java.util.Set)'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'Constraint',
	'l': 'updateOnSuccess(Timetable, TimeSlot, Subject, Set<Teacher>)',
	'u': 'updateOnSuccess(at.htl.timetableGenerator.Timetable,at.htl.timetableGenerator.TimeSlot,at.htl.timetableGenerator.Subject,java.util.Set)'
}, {
	'p': 'at.htl.timetableGenerator.output',
	'c': 'ExportData',
	'l': 'valueOf(String)',
	'u': 'valueOf(java.lang.String)'
}, {
	'p': 'at.htl.timetableGenerator.output',
	'c': 'ExportFormat',
	'l': 'valueOf(String)',
	'u': 'valueOf(java.lang.String)'
}, {
	'p': 'at.htl.timetableGenerator.output',
	'c': 'ExportData',
	'l': 'values()'
}, {'p': 'at.htl.timetableGenerator.output', 'c': 'ExportFormat', 'l': 'values()'}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'SchoolClass',
	'l': 'weeklySubjects'
}, {
	'p': 'at.htl.timetableGenerator',
	'c': 'WeeklySubjects',
	'l': 'WeeklySubjects(Subject, int)',
	'u': '%3Cinit%3E(at.htl.timetableGenerator.Subject,int)'
}];
updateSearchResults();