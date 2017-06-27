var baseUrl = "http://localhost:8080";
var blankNode = $("#template-container>li").clone();

refreshData();

function refreshData() {
    $.ajax({
        method: "GET",
        url: baseUrl + "/companies",
        success: function (result) {
            console.log(result);
            rewriteData(result);
        }
    });
    $("#modalForm").modal('hide');
}
function rewriteData(jsonData) {
    var treeRoot = $("#tree-root");
    treeRoot.html(" ");
    appendData(jsonData, treeRoot);
}

function appendData(jsonData, parent) {
    for (var i = 0; i < jsonData.length; i++) {
        parent.append(
            createNode(jsonData[i])
        );
    }
}

function createNode(dataItem) {
    var node = blankNode.clone();
    var scontent = dataItem.name + " | " + dataItem.estimatedEarnings + " | " + dataItem.totalEarnings;
    node.children(".Content").prepend(scontent);
    node.find("button").attr(
        "value",
        createValueFieldString(dataItem));
    node.find(".button-del").attr(
        "value",
        dataItem.name);
    var childArrayLength = dataItem.childCompanies.length;
    if (childArrayLength > 0)
            appendData(dataItem.childCompanies, node.children(".Container"));
    return node;
}
function createValueFieldString(dataItem) {
    return JSON.stringify(
        {
            name: dataItem.name,
            estimatedEarnings: dataItem.estimatedEarnings,
            parentName: dataItem.parentName
        }
    );
}
function parseJSONString(string) {
    return JSON.parse(string);
}

function onFormOpen(btn) {
    var data = {
            name: "",
            estimatedEarnings: "",
            parentName: ""
        },
        rawData = $(btn).attr("value");

    if (rawData != undefined) {
        data = parseJSONString(rawData);
        if ($(btn).hasClass("button-edit")) {
            $("#modalLabel").html("Editing: " + data.name);
            $("#companyForm").attr("onsubmit", "editCompany(event)");
        }
        else {
            $("#modalLabel").html("Add child company to: " + data.name);
            data.parentName = data.name;
            data.name = "";
            data.estimatedEarnings = "";
            $("#companyForm").attr("onsubmit", "addCompany(event)");
        }
    } else {
        if ($(btn).hasClass("button-add-main")) {
            $("#companyForm").attr("onsubmit", "addCompany(event)");
            $("#modalLabel").html("Add main company");
        }
    }

    $("#companyForm").find(":input[name=name]").attr("value", data.name);
    $("#companyForm").find(":input[name=estimatedEarnings]").attr("value", data.estimatedEarnings);
    $("#companyForm").find(":input[name=parentName]").attr("value", data.parentName);

    $("#modalForm").modal('show');
}

function editCompany(e) {
    var msg   = $(e.target).serialize();
    $.ajax({
        type: 'PUT',
        url: baseUrl + '/companies',
        data: msg,
        success: function (result) {
            console.log("Edited: " + result.name);
            refreshData()
        },
        error: function (xhr, str) {
            alert(str);
        }
    });
}
function addCompany(e) {
    var msg   = $(e.target).serialize();
    $.ajax({
        type: 'POST',
        url: baseUrl + '/companies',
        data: msg,
        success: function (result) {
            console.log("Added: " + result.name);
            refreshData()
        },
        error: function (xhr, str) {
            alert(str);
        }
    });
}
function deleteCompany(e) {
    var msg   = $(e).attr("value");
    $.ajax({
        type: 'GET',
        url: baseUrl + '/companies/del',
        data: {name:msg},
        success: function () {
            console.log("Deleted: " + msg);
            refreshData()
        },
        error: function (xhr, str) {
            alert(str);
        }
    });
}