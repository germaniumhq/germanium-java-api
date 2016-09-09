try {
    function __isElement(n) {
        return n && n.nodeType && n.ownerDocument
    }

    function __isElementList(l) {
        if (l && typeof l['length'] != 'undefined') {
            for (var __i = 0; __i < l.length; __i++) {
                if (!__isElement(l[__i])) {
                    return false;
                }
            }

            return true;
        }
    }

    var result = {
        data : (function() {
            %s
        }).apply(this, arguments),
        status : "SUCCESS"
    };

    return __isElement(result.data) || __isElementList(result.data) ?
        result.data :
        result;
} catch (e) {
    return {  // return the exception information in case of failure.
        status : "FAILURE",
        name : e.name,
        message : e.message
    };
}