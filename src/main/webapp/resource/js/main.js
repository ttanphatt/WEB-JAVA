
function deleteFollowpr(path) {

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
function deleteBinhLuanwpr(path) {

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

function deleteTaiKhoanpr(path) {

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
let editingId = null;
