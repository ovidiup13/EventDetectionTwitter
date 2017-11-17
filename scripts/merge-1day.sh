cd ..

# run Java program for filtering
gradle runMerging -Pwsargs="data/1day/clusters.sortedby.time.csv data/processed/1day/merged/"

# parse output of eval process
cd public/src
node index.js ../../data/processed/1day/merged/ ../public/assets/1day-merged.json

cd ../..