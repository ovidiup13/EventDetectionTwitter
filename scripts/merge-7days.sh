cd ..

# run Java program for filtering
gradle runMerging -Pwsargs="data/7days/clusters.sortedby.time.csv data/processed/7days/merged/"

# parse output of eval process
cd public/src
node index.js ../../data/processed/7days/merged/ ../public/assets/7days-merged.json

cd ../..