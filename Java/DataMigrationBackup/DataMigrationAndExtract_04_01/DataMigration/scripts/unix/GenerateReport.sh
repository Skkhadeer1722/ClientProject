echo ** Running DataMigration and Report service **
echo ** Process started Please wait....
cd ..
cd lib
java -jar DataMigrationProgram-0.0.1-SNAPSHOT.jar $1 $2 $3 $4
echo ** Process completed**
