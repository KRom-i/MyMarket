const inputPageNumber = document.getElementById("input_page_number");
const inputPageSize = document.getElementById("input_page_size");
const inputTitle = document.getElementById("input_title");
const inputMinPrice = document.getElementById("input_min_price");
const inputMaxPrice = document.getElementById("input_max_price");

function filterAddListeners(){
    inputTitle.addEventListener("input", (e)=>{
        inputPageNumber.value = 1;
        sendRequestPage()
    })
    inputMinPrice.addEventListener("input", (e)=>{
        inputPageNumber.value = 1;
        sendRequestPage()
    })
    inputMaxPrice.addEventListener("input", (e)=>{
        inputPageNumber.value = 1;
        sendRequestPage()
    })
}

function params(){
    // ?page=16&size=5&title=1&min=1&max=1000
    let params = "?&page=" + inputPageNumber.value + "&size=" + inputPageSize.value;

    if (inputTitle.value || inputMinPrice.value || inputMaxPrice.value){
        params+= "&title=" + inputTitle.value;
        params+= "&min=" + inputMinPrice.value;
        params+= "&max=" + inputMaxPrice.value;
    }

    window.history.pushState({},"", params);
    return params;
}
