function sendRequestGet(url){
    return fetch(url).then(response => {
        return response.json()
    })
}