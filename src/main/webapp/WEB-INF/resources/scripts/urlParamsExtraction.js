!(function () {
    let undefined;

    let DEFAULTS = {
        delimiter: '&',
        keyValueSeparator: '=',
        startAfter: '?',
    };

    String.prototype.extract = function (opts) {

        function filterInt(value) {
            return (/^(\-|\+)?([0-9]+|Infinity)$/.test(value)) ? Number(value) : NaN;
        }

        if (this.length <= 1) return;

        let options = opts || {},
            keyValuePairs = [],
            params = {};

        let delimiter = options.delimiter || DEFAULTS.delimiter;
        let keyValueSeparator = options.keyValueSeparator || DEFAULTS.keyValueSeparator;
        let startAfter = options.startAfter || DEFAULTS.startAfter;
        let limit = filterInt(options.limit) >= 1 ? options.limit : undefined;

        let querystringStartIndex = this.lastIndexOf(startAfter) + 1;
        let keyValueSeparatorFirstIndex = this.indexOf(keyValueSeparator, querystringStartIndex);

        if (keyValueSeparatorFirstIndex < 0) return;

        // scope of finding params only applicable to str
        let str = querystringStartIndex < 0 ? new String(this) : this.substring(querystringStartIndex);

        keyValuePairs = str.split(delimiter, limit);
        let kvPair, i = 0;
        for (let s = keyValuePairs.length; i < s; i++) {
            kvPair = keyValuePairs[i].split(keyValueSeparator, 2);
            // ignore any items after first value found, where key = kvPair[0], value = kvPair[1]
            let value = kvPair[1];
            params[kvPair[0]] = filterInt(value) ? filterInt(value) : value; // return int if value is parsable
        };
        return params;
    };
})();