function setNameAndSubmit(val) {
    $('#namesort').attr('value', val);
    $('#pricesort').attr('value', val);
    $('#form').submit();
}

function setThemeAndSubmit(val) {
    $('#themesort').attr('value', val);
    $('#form').submit();
}

function setPriceAndSubmit(val) {
    $('#pricesort').attr('value', val);
    $('#themesort').attr('value', "");
    $('#namesort').attr('value', val);
    $('#form').submit();
}

function setAll(val) {
    $('#all').attr('value', val);
    $('#form').submit();
}

function logout() {
    $.ajax({
        type: 'POST',
        url: "/Logout",
        async:false
    });
}

// function generateUrl(page) {
//     var currentLocation = window.location.href;
//     if(!currentLocation.match(/(page=[0-9]+)/g)) {
//         if (currentLocation.match(/\?/)) {
//             return currentLocation.concat("&page=").concat(page);
//
//         } else {
//             return currentLocation.concat("?page=").concat(page);
//         }
//     }else{
//         var match=currentLocation.match(/(page=[0-9]+)/g)[0];
//         return currentLocation.replace(match,"page=".concat(page));
//     }
// }
