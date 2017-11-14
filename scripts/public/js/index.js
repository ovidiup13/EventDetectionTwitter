var myHeaders = new Headers();

var myInit = {
    method: 'GET',
    headers: myHeaders,
    mode: 'cors',
    cache: 'default'
};

fetch("assets/1day-filtered.json", myInit)
    .then(response => response.json())
    .then(jsonResponse => createCharts(jsonResponse));

function createCharts(data) {
    createTotalClustersChart(data, "#day1-filtered-total-clusters-chart");
    createFMeasureChart(data, "#day1-filtered-fmeasure-chart");
    createPrecisionChart(data, "#day1-filtered-precision-chart");
    createRecallChart(data, "#day1-filtered-recall-chart");
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
            title: name,
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