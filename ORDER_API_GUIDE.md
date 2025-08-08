# Hướng dẫn sử dụng API Đặt hàng

## Tổng quan
API đặt hàng cho phép người dùng tạo đơn hàng mới với các tour được chọn.

## Endpoints

### 1. Tạo đơn hàng mới
**POST** `/api/orders`

#### Request Body
```json
{
  "userId": 1,
  "paymentMethod": "CASH",
  "note": "Ghi chú đơn hàng",
  "orderItems": [
    {
      "tourId": 1,
      "quantity": 2
    },
    {
      "tourId": 3,
      "quantity": 1
    }
  ]
}
```

#### Response Success (200)
```json
{
  "status": 200,
  "message": "Order created successfully",
  "data": {
    "id": 1,
    "userId": 1,
    "userName": "john_doe",
    "orderDate": "2024-01-15T10:30:00",
    "totalPrice": 5000000,
    "status": "PENDING",
    "paymentMethod": "CASH",
    "note": "Ghi chú đơn hàng",
    "createdAt": "2024-01-15T10:30:00",
    "updatedAt": "2024-01-15T10:30:00",
    "orderItems": [
      {
        "id": 1,
        "tourId": 1,
        "tourName": "Tour Hà Nội - Sapa",
        "quantity": 2,
        "unitPrice": 2500000,
        "subtotal": 5000000
      }
    ]
  }
}
```

#### Response Error (400/500)
```json
{
  "status": 400,
  "message": "User not found",
  "data": null
}
```

### 2. Lấy danh sách đơn hàng
**GET** `/api/orders`

#### Query Parameters
- `page`: Số trang (mặc định: 0)
- `size`: Số lượng item trên mỗi trang (mặc định: 10)
- `userId`: Lọc theo user ID
- `status`: Lọc theo trạng thái đơn hàng

## Cấu trúc dữ liệu

### OrderEntity
- `id`: ID đơn hàng
- `user`: Người đặt hàng (UserEntity)
- `orderDate`: Ngày đặt hàng
- `totalPrice`: Tổng tiền
- `status`: Trạng thái (PENDING, CONFIRMED, CANCELLED, COMPLETED)
- `paymentMethod`: Phương thức thanh toán
- `note`: Ghi chú
- `orderItems`: Danh sách các item trong đơn hàng

### OrderItemEntity
- `id`: ID item
- `order`: Đơn hàng chứa item này
- `tour`: Tour được đặt
- `quantity`: Số lượng
- `unitPrice`: Đơn giá
- `subtotal`: Thành tiền (quantity * unitPrice)

## Lưu ý
1. API sẽ tự động tính toán `subtotal` cho mỗi order item
2. API sẽ tự động tính toán `totalPrice` cho toàn bộ đơn hàng
3. Đơn hàng mới sẽ có trạng thái "PENDING"
4. Sử dụng cascade để lưu order items cùng với order
5. Validation: Kiểm tra user và tour tồn tại trước khi tạo đơn hàng
