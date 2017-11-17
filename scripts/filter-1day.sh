cd ..

# run Java program for filtering
gradle runFiltering -Pwsargs="data/1day/clusters.sortedby.clusterid.csv data/processed/1day/filtered/"

# parse output of eval process
cd public/src
node index.js ../../data/processed/1day/filtered/ ../public/assets/1day-filtered.json