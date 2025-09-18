/** @type {import('next').NextConfig} */
const nextConfig = {
    reactStrictMode: true,
    experimental: { typedRoutes: false },
    images: {
        remotePatterns: [
            {
                protocol: 'http',
                hostname: 'localhost',
                port: '8088',
                pathname: '/inventory/**',
            },
            {
                protocol: 'http',
                hostname: 'localhost',
                port: '8088',
                pathname: '/admin/**',
            },
        ],
    },
};
export default nextConfig;
