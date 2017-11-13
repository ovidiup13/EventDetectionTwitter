const PythonShell = require("python-shell");
const path = require("path");

const evalPath = "../../data/eval/";
const pyScript = "eval.py";

const options = {
    scriptPath: evalPath
};

module.exports = {
    runEvaluation: function (input) {

        options.args = [input, path.join(evalPath, 'events.rel'), path.join(evalPath, 'events.desc'), path.join(evalPath, 'events.category')];

        return new Promise((resolve, reject) => {
            PythonShell.run(pyScript, options, (err, data) => {
                if (err) {
                    reject(err);
                }
                resolve(data);
            });
        });
    }
};