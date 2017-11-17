const fs = require("fs");

module.exports = {
    parseOutputFile: function (path) {
        return new Promise(function (resolve, reject) {
            fs.readFile(path, (err, data) => {
                if (err) {
                    reject(err);
                }
                const results = parseToJSON(data.toString().split("\n"));
                resolve(results);
            });
        });
    },
    parseOutput: function (data) {
        return parseToJSON(data);
    }
};

function parseToJSON(data) {
    const result = {
        events: {},
        clusters: {},
        stats: {}
    };

    for (let line of data) {
        if (new RegExp(/ - Of [0-9]+ events/).test(line)) {
            const items = line.split(" ");
            result.events.total = parseInt(items[3]);
            result.events.detected = parseInt(items[5]);
        } else if (new RegExp(/ - Of [0-9]+ clusters/).test(line)) {
            const items = line.split(" ");
            result.clusters.total = parseInt(items[3]);
            result.clusters.matched = parseInt(items[5]);
        } else if (new RegExp(/Event Recall:/).test(line)) {
            const items = line.split("\t");
            result.stats.recall = parseFloat(items[2]);
        } else if (new RegExp(/Cluster Precision:/).test(line)) {
            const items = line.split("\t");
            result.stats.precision = parseFloat(items[1]);
        } else if (new RegExp(/Overall F-Measure:/).test(line)) {
            const items = line.split("\t");
            result.stats.fmeasure = parseFloat(items[1]);
        }
    }

    return result;
}