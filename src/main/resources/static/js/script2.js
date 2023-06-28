function toastModal(txt, sec) {
    let secs = 1000;
    if(sec == undefined || sec == null) secs = 1000;
    else secs = sec;

    const newDiv = document.createElement('div');
    const newText = document.createTextNode(txt);

    newDiv.appendChild(newText);

    $(newDiv).css({
        "position":"fixed",
        "top":"50%",
        "left":"50%",
        "width":"fit-content",
        "max-width":"calc(100% - 60vw)",
        "transform":"translateX(-50%)",
        "backgroundColor":"rgba(43, 53, 53, 0.7)",
        "backdrop-filter":"blur(10px)",
        // "box-shadow":"0px 0px 20px rgb(43 53 53 / 10%)",
        "padding":"1rem 30px",
        "border-radius":"10px",
        "color":"var(--white)",
        "font-size":"1rem",
        "z-index":"99999",
        "text-align":"center",
        "line-height":"1.5rem",
        "font-weight":"500"
    });

    // $(newDiv).css({"position":"fixed","top":"49%","left":"50%","transform":"translateX(-50%)","background-color":"#276cad","padding":"12px 30px","border-radius":"20px","color":"#fff","z-index":99999});

    document.body.appendChild(newDiv);

    setTimeout(function () {
        $(newDiv).animate({
            opacity: "hide"
        });
    }, secs);
}

function fConvertDateFrom(string) {
    let year = string.substr(0, 4);
    let month = string.substr(5, 2);

    let day = string.substr(8, 2);

    let returnDate = new Date(year, parseInt(month) - 1, day);
    returnDate.setHours(returnDate.getHours() + 9);

    let returnStr = returnDate.toISOString().replace("T", " ").replace(/\..*/, '');
    return returnStr;
}

function fConvertDateTo(string) {
    let year = string.substr(0, 4);
    let month = string.substr(5, 2);

    let day = (parseInt(string.substr(8, 2)) + 1) + '';

    let returnDate = new Date(year, parseInt(month) - 1, day);
    returnDate.setHours(returnDate.getHours() + 9);

    let returnStr = returnDate.toISOString().replace("T", " ").replace(/\..*/, '');
    return returnStr;
}

function fPagination(data, page) {
    let num = "";
    let reNum = parseInt(data.number / 5);
    let pre = "";
    let next = "";

    // 이전버튼 (5페이지씩 이동, 처음으로)
    if (reNum >= 1 && page > 5) {
        pre = "<li><a class='cupoint' gopage='" + (reNum * 5) + "'><i class='fa-solid fa-chevron-left'></i></a></li>";
    }

    // 다음버튼 (5페이지씩 이동, 마지막으로)
    if (parseInt((data.totalPages - 1) / 5) != reNum && data.totalPages > 5) {
        next = "<li><a class='cupoint' gopage='" + ( reNum * 5 + 6 ) + "'><i class='fa-solid fa-chevron-right'></i></a></li>";
    }

    for ( let idx = 1; idx <= data.totalPages; idx++ ) {
        if ( idx <= reNum * 5 + 5 && idx > reNum * 5 ) {
            if (idx == page) {
                num += "<li class='on'><a class='cupoint' gopage=" + idx + ">" + idx + "</a></li>";
            } else {
                num += "<li><a class='cupoint' gopage=" + idx + ">" + idx + "</a></li>";
            }
        }
    }
    $("#pagination").html("<ul class='paging'>" + pre + num + next + "</ul>");
}

// 천단위 콤마
function fcomma(str) {
    str = String(str);
    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}

// 천단위 콤마 제거
function uncomma(str) {
    str = String(str);
    return str.replace(/[^\d]+/g, '');
}

function fConvertHour(second) {
    let hour = parseInt((second % 3600) / 60) < 10 ? '0' + parseInt((second % 3600) / 60) : parseInt((second % 3600) / 60);

    return hour;
}
function fConvertMin(second) {
    let min = parseInt(second % 60) < 10 ? '0' + parseInt(second % 60) : parseInt(second % 60);

    return min;
}

//cost 비용 불러오기
function getAllCost() {
    let busFare;
    let guideFee;
    let insuranceFee;

    $.ajax({
        type: "GET",
        url: "/api/user/unit",
        async: false,
        success: function (data) {
            // console.log(data);

            busFare=data.busFare;
            guideFee=data.guideFee;
            insuranceFee=data.insuranceFee;
        }
    });
    return [busFare, guideFee, insuranceFee];
}

//이메일 정규식 체크
function femailCheck(email) {
    let reg = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

    return reg.test(email);
    //return reg.test(email);
}