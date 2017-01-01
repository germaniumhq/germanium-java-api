window.console = {
    log : function() {
        var items = new Array(arguments.length);

        for (var i = 0; i < arguments.length; i++) {
            items[i] = arguments[i];
        }

        window._lastlog_message = items;
    },

    error: function() {
        var items = new Array(arguments.length);

        for (var i = 0; i < arguments.length; i++) {
            items[i] = arguments[i];
        }

        window._lasterror_message = items;
    }
};
