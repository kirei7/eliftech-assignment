var baseUrl = "localhost:8080";
console.log("call");
refreshData();

function rewriteData(msg) {
    console.log("call3");
    console.log(msg);
}

function refreshData() {
    $.ajax({
        method: "GET",
        url: baseUrl + "/companies",
        success: function (result) {
            console.log("call2");
            rewriteData(result);
        }
    });
}