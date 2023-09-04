function deleteBViet(path) {
    if (confirm("Bạn chắc chắn xóa không?") === true) {
        fetch(path, {
            method: "delete"
        }).then(res => {
            if (res.status === 204)
                location.reload();
            else
                alert("Something wrong!!!");
        });
    }
}
function deleteFollowpr(path){
    
        if (confirm("Bạn chắc chắn xóa không?") === true) {
        fetch(path, {
            method: "delete"
            
        }).then(res => {
            if (res.status === 204)
                location.reload();
            else
                alert("Something wrong!!!");
        });
    }
}
//function addFl(path){
//    
//        if (confirm("Bạn có muốn thêm follow không") === true) {
//        fetch(path, {
//            method: "post"
//        }).then(res => {
//            if (res.status === 204)
//                location.reload();
//            else
//                alert("Something wrong!!!");
//        });
//    }
//}