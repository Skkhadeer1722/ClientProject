@echo off
set arg1=%1
set _date=%DATE%-%TIME%
set _date=%_date:/=-%
set _date=%_date: =-%
set _date=%_date::=%
set _date=%_date:.=%

echo ** Running DataMigrationa and Report service **
echo ** Process started Please wait......
cd ..
cd lib
java -jar DataMigrationProgram-0.0.1-SNAPSHOT.jar datamigration %arg1% 2>&1 > ..\logs\DataMigration_script_log_%_date%.txt && type ..\logs\DataMigration_script_log_%_date%.txt
echo ** Process completed**

pause
EXIT