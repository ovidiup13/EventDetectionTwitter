cd ..

# run Java program for filtering
gradle runFiltering -Pwsargs="data/7days/clusters.sortedby.clusterid.csv data/processed/7days/filtered/"

# parse output of eval process
cd public/src
node index.js ../../data/processed/7days/filtered/ ../public/assets/7days-filtered.json