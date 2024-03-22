echo ** Running DataMigration and Report service **.
echo ** Process started Please wait......
echo $1
cd ..
cd lib
java -jar DataMigrationProgram-0.0.1-SNAPSHOT.jar datamigration $1 
echo ** Process completed**
