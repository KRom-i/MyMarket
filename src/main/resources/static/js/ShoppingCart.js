const table = document.getElementById("cart_table")
const requestCartURL = "/getCart"
const requestProductURL = "/getCart"

getShoppingCart()

function getShoppingCart(){
    const  xhr = new XMLHttpRequest()
    xhr.responseType = "json"

    xhr.open("GET", requestCartURL)

    xhr.onload = () => {
        if (xhr.status >= 400){
            console.error(xhr.response)
            return
        }
        updateTable(xhr.response)
    }

    xhr.onerror = () => {
        console.error(xhr.response)
    }

    xhr.send()
}

function updateTable(ShoppingCart){

    console.log(ShoppingCart)

    if (ShoppingCart.empty){
        return
    }

    table.innerHTML = ""

    addHead()

    for (let i = 0; i < ShoppingCart.items.length; i++) {
        const item = ShoppingCart.items[i]
        addRow(item)
    }
}

function addHead(){
    const tableHead = document.createElement("thead")
    table.append(tableHead)

    addCollum(tableHead, "Категория")
    addCollum(tableHead, "Название товара")
    addCollum(tableHead, "Количество")
    addCollum(tableHead, "Цена")
    addCollum(tableHead, "Действие")
}

function addRow(item){
    const row = document.createElement("tr")
    table.append(row)

    addCollum(row, item.category)
    addCollum(row, item.title)
    addCollum(row, item.num)
    addCollum(row, item.sum)
    addButton(row, item)
}

function addCollum(node, value){
    const collum = document.createElement("td")
    collum.textContent = value
    node.append(collum)
}

function addButton(row, item){
    const collum = document.createElement("td")
    row.append(collum)
    collum.className = "cart_buttons"

    const buttonDic = document.createElement("div")
    collum.append(buttonDic)
    buttonDic.className = "btn_dic btn_card"

    const buttonInc = document.createElement("div")
    collum.append(buttonInc)
    buttonInc.className = "btn_inc btn_card"
    buttonInc.addEventListener("click", () => sendRequestPost(item))

    const buttonRemove = document.createElement("div")
    collum.append(buttonRemove)
    buttonRemove.className = "btn_remove btn_card"
    buttonRemove.addEventListener("click", () => rowClear(row))

}

function rowClear(row){
    row.remove()
    // cartRequest()
}

function sendRequestPost(item){

    const  xhr = new XMLHttpRequest()
    xhr.responseType = "json"

    console.log(item)

    xhr.open("POST", requestProductURL)

    xhr.onload = () => {
        if (xhr.status >= 400){
            console.error(xhr.response)
        } else {
            console.log(xhr.response)
        }

    }

    xhr.onerror = () => {
        console.error(xhr.response)
    }

    xhr.send(item)
}


