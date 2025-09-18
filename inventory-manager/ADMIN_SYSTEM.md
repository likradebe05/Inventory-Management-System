# Admin System Documentation

## Overview
The inventory management system now includes a comprehensive admin panel for managing products, customers, orders, and administrative users.

## Admin Features

### 🏠 Admin Dashboard (`/admin`)
- Overview statistics (total products, customers, orders, admins, low stock, recent orders)
- Quick navigation to all admin modules
- Authentication guard (only accessible to admin users)

### 📦 Product Management (`/admin/products`)
- **View All Products**: Complete list with search, sort, and filter capabilities
- **Add New Product** (`/admin/products/add`): Create products with image upload
- **Edit Product** (`/admin/products/edit/[id]`): Update product details and images
- **Delete Products**: Remove products with confirmation
- **Image Support**: Upload, preview, and manage product images
- **Inventory Tracking**: Stock levels with color-coded quantity indicators

### 👥 Customer Management (`/admin/customers`)
- **View All Customers**: Complete customer list with search functionality
- **Customer Details**: Name, email, phone, address, order history
- **Customer Statistics**: Total customers, active customers, new registrations
- **Order Tracking**: View customer order counts and activity

### 📋 Order Management (`/admin/orders`)
- **View All Orders**: Complete order list with status tracking
- **Order Filtering**: Filter by status (pending, processing, shipped, delivered, cancelled)
- **Status Updates**: Change order status directly from the interface
- **Order Details**: Customer info, items, total amount, order date
- **Order Statistics**: Status-based counts and overview

### 👨‍💼 Admin User Management (`/admin/admins`)
- **View Admin Users**: List of all administrator accounts
- **Add New Admin** (`/admin/admins/add`): Create new admin accounts
- **Delete Admins**: Remove admin accounts (except own account)
- **Security Features**: Cannot delete own account, full privilege warnings

## API Integration

### Admin API (`/api/admin.ts`)
Comprehensive API functions for:
- **User Management**: `getAdminUsers()`, `createAdminUser()`, `deleteAdminUser()`
- **Product Management**: `getAllProducts()`, `createProductWithImage()`, `updateProductWithImage()`, `deleteProduct()`
- **Customer Data**: `getAllCustomers()`, `getCustomerById()`
- **Order Management**: `getAllOrders()`, `updateOrderStatus()`
- **Dashboard Stats**: `getDashboardStats()`

### Backend Integration
All admin functions integrate with existing Spring Boot backend:
- Uses JWT authentication with admin role verification
- Integrates with existing image upload system
- Compatible with existing product, customer, and order entities

## Security Features

### Authentication & Authorization
- **JWT Token Verification**: All pages verify admin authentication
- **Role-Based Access**: Only users with `ADMIN` role can access admin pages
- **Auto-Redirect**: Unauthorized users redirected to login
- **Session Management**: Handles token expiration and logout

### Admin Account Security
- **Self-Protection**: Admins cannot delete their own accounts
- **Privilege Warnings**: Clear warnings when creating new admin accounts
- **Secure Creation**: New admin accounts require all necessary fields

## User Interface

### Design System
- **Consistent Styling**: Tailwind CSS with cohesive color scheme
- **Responsive Design**: Works on desktop, tablet, and mobile
- **Loading States**: Proper loading indicators for all operations
- **Error Handling**: User-friendly error messages and validation

### Navigation
- **Breadcrumb Navigation**: Clear path navigation
- **Quick Actions**: Easy access to common operations
- **Search & Filter**: Efficient data discovery
- **Bulk Operations**: Where applicable (future enhancement)

## File Structure

```
src/app/admin/
├── page.tsx                    # Main dashboard
├── products/
│   ├── page.tsx               # Product list
│   ├── add/page.tsx           # Add product
│   └── edit/[id]/page.tsx     # Edit product
├── customers/
│   └── page.tsx               # Customer list
├── orders/
│   └── page.tsx               # Order management
└── admins/
    ├── page.tsx               # Admin list
    └── add/page.tsx           # Add admin

src/api/
└── admin.ts                   # Admin API functions
```

## Usage Instructions

### For Developers
1. **Backend Setup**: Ensure Spring Boot backend is running with admin endpoints
2. **Environment**: Set `NEXT_PUBLIC_API_URL` if using non-default backend URL
3. **Build**: Run `npm run build` to verify compilation
4. **Development**: Use `npm run dev` for development server

### For Administrators
1. **Login**: Use admin credentials at `/login`
2. **Dashboard**: Access admin panel at `/admin`
3. **Product Management**: Add/edit products with images at `/admin/products`
4. **User Management**: Manage admins at `/admin/admins`
5. **Monitoring**: Track orders and customers from respective admin pages

## Future Enhancements
- **Bulk Operations**: Multi-select for bulk actions
- **Advanced Analytics**: Charts and detailed reporting
- **Admin Profile Management**: Edit admin account details
- **Audit Logs**: Track admin actions and changes
- **Email Notifications**: Order status and low stock alerts
- **Export Features**: CSV/PDF export of data
- **Advanced Filters**: Date ranges, custom criteria
- **Dashboard Customization**: Personalized admin dashboards

## Technical Notes
- **Image Handling**: Automatic image optimization and fallbacks
- **Type Safety**: Full TypeScript support with proper typing
- **Error Boundaries**: Graceful error handling throughout
- **Performance**: Optimized builds with code splitting
- **SEO**: Proper meta tags and page structure
- **Accessibility**: ARIA labels and keyboard navigation support

The admin system provides a complete management interface for the inventory system while maintaining security, usability, and extensibility.