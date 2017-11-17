(function () {

    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        var target = $(e.target).attr("href");
        switch (target) {
            case "#1day-filtered":
                fetch("assets/1day-filtered.json")
                    .then(response => response.json())
                    .then(jsonResponse => create1DayFilteredCharts(jsonResponse));
                break;
            case "#1day-merged":
                fetch("assets/1day-merged.json")
                    .then(response => response.json())
                    .then(jsonResponse => create1DayMergedCharts(jsonResponse));
                break;
            case "#7days-filtered":
                fetch("assets/7days-filtered.json")
                    .then(response => response.json())
                    .then(jsonResponse => create7DaysFilteredCharts(jsonResponse));
                break;
            case "#7days-merged":
                fetch("assets/7days-merged.json")
                    .then(response => response.json())
                    .then(jsonResponse => create7DaysMergedCharts(jsonResponse));
                break;
        }
    });

    fetch("assets/1day-filtered.json")
        .then(response => response.json())
        .then(jsonResponse => create1DayFilteredCharts(jsonResponse));

    function create7DaysFilteredCharts(data) {
        createEventsChart(data, "#day7-filtered-events-detected-chart");
        createTotalClustersChart(data, "#day7-filtered-total-clusters-chart");
        createFMeasureChart(data, "#day7-filtered-fmeasure-chart");
        createPrecisionChart(data, "#day7-filtered-precision-chart");
        createRecallChart(data, "#day7-filtered-recall-chart");
    }

    function create7DaysMergedCharts(data) {
        createEventsChart(data, "#day7-merged-events-detected-chart");
        createTotalClustersChart(data, "#day7-merged-total-clusters-chart");
        createFMeasureChart(data, "#day7-merged-fmeasure-chart");
        createPrecisionChart(data, "#day7-merged-precision-chart");
        createRecallChart(data, "#day7-merged-recall-chart");
    }

    function create1DayFilteredCharts(data) {
        createEventsChart(data, "#day1-filtered-events-detected-chart");
        createTotalClustersChart(data, "#day1-filtered-total-clusters-chart");
        createFMeasureChart(data, "#day1-filtered-fmeasure-chart");
        createPrecisionChart(data, "#day1-filtered-precision-chart");
        createRecallChart(data, "#day1-filtered-recall-chart");
    }

    function create1DayMergedCharts(data) {
        createEventsChart(data, "#day1-merged-events-detected-chart");
        createTotalClustersChart(data, "#day1-merged-total-clusters-chart");
        createFMeasureChart(data, "#day1-merged-fmeasure-chart");
        createPrecisionChart(data, "#day1-merged-precision-chart");
        createRecallChart(data, "#day1-merged-recall-chart");
    }

    function createEventsChart(data, elemId) {
        const name = "Events Detected";
        const chartType = "bar";
        const color = "orange";

        const labels = data.map(elem => elem.file);
        const values = data.map(elem => elem.data.events.detected);

        const chartData = {
            labels: labels,
            datasets: [{
                title: name,
                color: color,
                values: values
            }]
        };

        createChart(elemId, name, chartData, chartType);
    }

    function createTotalClustersChart(data, elemId) {
        const name = "Total Clusters";
        const chartType = "bar";
        const color = "violet";

        const labels = data.map(elem => elem.file);
        const values = data.map(elem => elem.data.clusters.total);

        const chartData = {
            labels: labels,
            datasets: [{
                title: "Clusters",
                color: color,
                values: values
            }]
        };

        createChart(elemId, name, chartData, chartType);
    }

    function createFMeasureChart(data, elemId) {
        const name = "Overall F Measure";
        const chartType = "line";
        const color = "light-blue";

        const labels = data.map(elem => elem.file);
        const values = data.map(elem => elem.data.stats.fmeasure);

        const chartData = {
            labels: labels,
            datasets: [{
                title: name,
                color: color,
                values: values
            }]
        };

        createChart(elemId, name, chartData, chartType);
    }

    function createPrecisionChart(data, elemId) {

        const name = "Precision";
        const chartType = "line";
        const color = "red";

        const labels = data.map(elem => elem.file);
        const values = data.map(elem => elem.data.stats.precision);

        const chartData = {
            labels: labels,
            datasets: [{
                title: name,
                color: color,
                values: values
            }]
        };
        createChart(elemId, name, chartData, chartType);
    }

    function createRecallChart(data, elemId) {
        const name = "Recall";
        const chartType = "line";
        const color = "green";

        const labels = data.map(elem => elem.file);
        const values = data.map(elem => elem.data.stats.recall);

        const chartData = {
            labels: labels,
            datasets: [{
                title: name,
                color: color,
                values: values
            }]
        };

        createChart(elemId, name, chartData, chartType);
    }

    function createChart(parentId, title, data, type) {
        return new Chart({
            parent: parentId, // or a DOM element
            title: title,
            data: data,
            type: type, // or 'line', 'scatter', 'pie', 'percentage'
            height: 250
        });
    }
})();