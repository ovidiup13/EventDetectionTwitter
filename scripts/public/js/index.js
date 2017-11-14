var myHeaders = new Headers();

var myInit = {
    method: 'GET',
    headers: myHeaders,
    mode: 'cors',
    cache: 'default'
};

fetch("assets/1day.json", myInit)
    .then(response => response.json())
    .then(jsonResponse => createCharts(jsonResponse));

function createCharts(data) {
    // create F-Measure Chart
    createFMeasureChart(data);
}

function createFMeasureChart(data) {

    const chartData = {
        labels: data.map(elem => elem.file),
        datasets: [{
                title: "Overall F-Measure",
                color: "light-blue",
                values: data.map(elem => elem.data.stats.fmeasure)
            },
            {
                title: "Precision",
                color: "red",
                values: data.map(elem => elem.data.stats.precision)
            },
            {
                title: "Recall",
                color: "green",
                values: data.map(elem => elem.data.stats.recall)
            }
        ]
    };

    const chart = new Chart({
        parent: '#chart', // or a DOM element
        title: "1 Day Process Results",
        data: chartData,
        type: 'line', // or 'line', 'scatter', 'pie', 'percentage'
        height: 250
    });
}