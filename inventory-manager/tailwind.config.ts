import type { Config } from 'tailwindcss'

const config: Config = {
    content: [
        './src/pages/**/*.{js,ts,jsx,tsx,mdx}',
        './src/components/**/*.{js,ts,jsx,tsx,mdx}',
        './src/app/**/*.{js,ts,jsx,tsx,mdx}',
    ],
    theme: {
        extend: {
            colors: {
                brand: {
                    50: '#eef8ff',
                    100: '#d7ecff',
                    200: '#b2dbff',
                    300: '#84c3ff',
                    400: '#4aa4ff',
                    500: '#1e86ff',
                    600: '#0f6ce6',
                    700: '#0c55b4',
                    800: '#0f4a91',
                    900: '#123e73',
                },
            },
            boxShadow: {
                soft: '0 8px 30px rgba(0, 0, 0, 0.08)',
            }
        },
    },
    plugins: [],
}
export default config
