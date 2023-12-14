// lấy dữ liệu
const getDAta = () => {
  $.ajax({
    method: "GET",
    url: "http://localhost:8080/category",
    dataType: "JSON",
    success: (response) => {
      let _html = ``;
      response.forEach((element, index) => {
        _html += `  <tr>
                                <td>${index + 1}</td>
                                <td>${element.categoryName}</td>
                                <td>${
                                  element.categoryStatus ? "Hiện" : "Ẩn"
                                }</td>
                                <td>
                                    <button type="button" class="btn_edit" onclick = editCate(${
                                      element.categoryId
                                    })>Edit</button>
                                    <button type="button" class="btn_delete" onclick = deleteCate(${
                                      element.categoryId
                                    })>Xoá</button>
                                </td>
                            </tr>`;
      });
      $("#table").html(_html);
    },
    error: (err) => {
      console.log(err);
    },
  });
};

getDAta();

// Thêm mới
const create = () => {
  // lấy giá trị từ ô input
  let category_id = $("#categoryId").val();
  let category_name = $("#categoryName").val();
  let completedEditInput = $('input[name="check"]:checked').val();
  let _data = {
    categoryId: category_id,
    categoryName: category_name,
    categoryStatus: completedEditInput,
  };
  _data = JSON.stringify(_data);

  // hãy thêm một biến để theo dõi giá trị của class.
  let isUpdate = $(".btn_add").hasClass("update");
  if (isUpdate) {
    // Lấy giá trị id từ nơi đó bạn lưu nó, chẳng hạn như thuộc tính data-id của nút.
    let id = $(".btn_add").attr("data-id");
    $.ajax({
      method: "PUT",
      url: `http://localhost:8080/category/${id}`,
      dataType: "JSON",
      contentType: "application/json",
      data: _data,
      success: (response) => {
        console.log(response);
        Swal.fire({
          position: "top-center",
          icon: "success",
          title: "Update thành công",
          showConfirmButton: false,
          timer: 1500,
        });
        getDAta();
        $(".btn_add").removeClass("update");
        $(".btn_add").text("Add");
        $(".btn_add").removeAttr("data-id");
        $("#categoryName").val("");
      },
      error: (err) => {
        console.log(err);
      },
    });
  } else {
    $.ajax({
      method: "POST",
      url: `http://localhost:8080/category`,
      dataType: "JSON",
      contentType: "application/json",
      data: _data,
      success: (response) => {
        console.log(response);
        Swal.fire({
          position: "top-center",
          icon: "success",
          title: "Thêm mới thành công",
          showConfirmButton: false,
          timer: 1500,
        });
        getDAta();
        $("#categoryName").val("");
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
};

// xoa
const deleteCate = (id) => {
  $.ajax({
    method: "DELETE",
    url: `http://localhost:8080/category/${id}`,
    dataType: "JSON",
    success: (response) => {
      getDAta();
    },
    error: (err) => {
      console.log(err);
    },
  });
};
//hàm edit

const editCate = (id) => {
  let idEdit = id;
  console.log(idEdit);
  $.ajax({
    method: "GET",
    url: `http://localhost:8080/category/${idEdit}`,
    dataType: "JSON",
    success: (response) => {
      // Đổ dữ liệu từ response vào form
      $("#categoryId").val(response.categoryId);
      $("#categoryName").val(response.categoryName);

      // Sửa lỗi ở đây: .prop() không hoạt động với NodeList, sử dụng .filter() thay thế
      $("input[name='check']")
        .filter(`[value='${response.categoryStatus}']`)
        .prop("checked", true);

      // Sửa lỗi ở đây: .classList không phải là phương thức của jQuery, sử dụng .addClass() thay thế
      $(".btn_add").addClass("update");
      $(".btn_add").text("Update");
      $(".btn_add").attr("data-id", idEdit);
    },
    error: (err) => {
      console.log(err);
    },
  });
};

// Tìm kiếm
const search = () => {
  let search = $("#search").val();
  const searchData = {
    name: search,
  };

  $.ajax({
    method: "GET",
    url: "http://localhost:8080/category/search",
    dataType: "json", // Sử dụng "json" thay vì "JSON"
    data: searchData,
    success: (response) => {
      let _html = ``;
      response.forEach((element, index) => {
        _html += `  <tr>
                                <td>${index + 1}</td>
                                <td>${element.categoryName}</td>
                                <td>${
                                  element.categoryStatus ? "Hiện" : "Ẩn"
                                }</td>
                                <td>
                                    <button type="button" class="btn_edit" onclick = "editCate(${
                                      element.id
                                    })">Edit</button>
                                    <button type="button" class="btn_delete" onclick = "deleteCate(${
                                      element.id
                                    })">Xoá</button>
                                </td>
                            </tr>`;
      });
      $("#table").html(_html);
      $("#search").val("");
    },
    error: (err) => {
      console.log(err);
    },
  });
};
