echo ** Running DataExtractionService**.
echo ** Process started Please wait......
dt=$(date '+%d-%m-%Y-%H%M%S');
echo $1
cd ..
mkdir -p logs
cd lib
java -jar DataExtractProgram-0.0.1-SNAPSHOT.jar $1 >../logs/DataExtract_Script_log_$dt.txt
echo ** Process completed**
cat ../logs/DataExtract_Script_log_$dt.txt
