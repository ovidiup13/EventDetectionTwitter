echo "Running filtering for 1 day data"
./filter-1day.sh
echo "Running filtering for 7 days data"
./filter-7days.sh
echo "Running merging for 1 day data"
./merge-1day.sh
echo "Running merging for 7 days data"
./merge-7days.sh