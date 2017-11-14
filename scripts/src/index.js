const fs = require("fs");
const path = require("path");

const evaluation = require("./eval");
const parse = require("./parse");

const inputPath = process.argv[2];

const outputFile = process.argv[3];
const outputPath = "../public/assets";

fs.readdir(inputPath, (err, items) => {
    runEval(items).then(data => {
        fs.writeFile(path.join(outputPath, outputFile), JSON.stringify(data), err => {
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
                    file: file,
                    data: parse.parseOutput(data)
                };
            }).catch(err => {
                console.error(err);
            }));
    }

    return Promise.all(results);
}