/*
 * Zvyraznuje hornu listu (navbar) kde sa aktualne nachadzam.
 */

var MYLIBRARY = MYLIBRARY || (function(){
    var _args = {};

    return {
        init : function(Args) {
            _args = Args;
        },
        activeNavbar : function() {
        	for (i = 0; i < _args.length; i++){
        		var active = document.getElementById(_args[i]);
        		active.className += " active";
        	}
        }
    };
}());
