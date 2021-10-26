
function updatePagerBottom(page){

    document.getElementById("pager_left").innerHTML =
        "<div id=\"btn_left\" class=\"btn_page\"></div>";
    document.getElementById("pager_right").innerHTML =
        "<div id=\"btn_right\" class=\"btn_page\"></div>";

    let num = page.pageable.pageNumber;

    const btnFirst = document.createElement("div");
    btnFirst.innerHTML = "&lt;";
    if (!page.first){
        addEventListenerThisBottom(btnFirst, num);
        darkStyleThisBottom(btnFirst);
        const btnLeft = document.getElementById("btn_left");
        displayThisBottom(btnLeft);
        addEventListenerThisBottom(btnLeft, num);
    } else {
        lightStyleThisBottom(btnFirst);
    }

    const pageNumber = document.createElement("div");
    pageNumber.textContent = num + 1;
    pageNumber.className = "btn_light page_number";

    const btnNext = document.createElement("div");
    btnNext.innerHTML = "&gt;";
    if (!page.last){
        addEventListenerThisBottom(btnNext, num + 2);
        darkStyleThisBottom(btnNext);
        const btnRight = document.getElementById("btn_right");
        displayThisBottom(btnRight);
        addEventListenerThisBottom(btnRight, num + 2);
    } else {
        lightStyleThisBottom(btnNext);
    }

    const pagerBottom = document.getElementById("pager_bottom");
    pagerBottom.textContent = "";
    pagerBottom.append(btnFirst, pageNumber, btnNext);

    if (page.totalPages <= 1){
        pagerBottom.style = "opacity: 0;"
        } else {
        pagerBottom.style = "opacity: 1;"
    }
}

function addEventListenerThisBottom(btn, numPage){
    btn.addEventListener("click", () =>{
        window.scrollTo(0,0);
        inputPageNumber.value = numPage;
        sendRequestPage();
    },)
}

function displayThisBottom(btn){
    btn.className += " btn_page_display";
}

function darkStyleThisBottom(btn){
    btn.className = "btn_dark pager_bottom";
}

function lightStyleThisBottom(btn){
    btn.className = "btn_light pager_bottom";
}
