const fs = require("fs");
const path = require("path");

const evaluation = require("./eval");
const parse = require("./parse");

const inputPath = process.argv[2];
const outputFile = process.argv[3];

fs.readdir(inputPath, (err, items) => {
    console.log(items);
    runEval(items).then(data => {
        data.sort((d1, d2) => parseInt(d1.file) - parseInt(d2.file));
        fs.writeFile(outputFile, JSON.stringify(data), err => {
            if (err) {
                console.error(err);
            }
        });
    });
});

function runEval(files) {
    const results = [];

    for (let file of files) {
        results.push(evaluation.runEvaluation(path.join(inputPath, file))
            .then(data => {
                return {
                    file: file.substring(0, file.indexOf(".")),
                    data: parse.parseOutput(data)
                };
            }).catch(err => {
                console.error(err);
            }));
    }

    return Promise.all(results);
}