import React from "react";
import { PieChart, Pie, Legend, Tooltip, Cell } from "recharts";
import { BarChart, Bar, XAxis, YAxis, CartesianGrid } from "recharts";

export default function Gist(posted) {
    console.log(posted)
    const data = [
        { name: "Posted", value: posted.posted},
        { name: "Completed", value: posted.completed},
    ];

    const COLORS = ['#0088FE', '#00C49F', '#FFBB28', '#FF8042'];

    const RADIAN = Math.PI / 180;
    const renderCustomizedLabel = ({ cx, cy, midAngle, innerRadius, outerRadius, percent, index }) => {
        const radius = innerRadius + (outerRadius - innerRadius) * 0.5;
        const x = cx + radius * Math.cos(-midAngle * RADIAN);
        const y = cy + radius * Math.sin(-midAngle * RADIAN);

        return (
            <text x={x} y={y} fill="white" textAnchor={x > cx ? 'start' : 'end'} dominantBaseline="central">
                {`${(percent * 100).toFixed(0)}%`}
            </text>
        );
    };

    return (
        <div style={{ textAlign: "center" }}>
            <h1>User Statistics</h1>

            <div className="chart">
                <PieChart width={400} height={400}>
                    <Pie
                        data={data}
                        cx="50%"
                        cy="50%"
                        labelLine={false}
                        label={renderCustomizedLabel}
                        outerRadius={80}
                        fill="#8884d8"
                        dataKey="value"
                    >
                        {data.map((entry, index) => (
                            <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
                        ))}
                    </Pie>
                    <Tooltip />
                    <Legend />
                </PieChart>

                {/*<BarChart*/}
                {/*    width={500}*/}
                {/*    height={300}*/}
                {/*    data={data}*/}
                {/*    margin={{*/}
                {/*        top: 5,*/}
                {/*        right: 30,*/}
                {/*        left: 20,*/}
                {/*        bottom: 5*/}
                {/*    }}*/}
                {/*    barSize={20}*/}
                {/*>*/}
                {/*    <XAxis*/}
                {/*        dataKey="name"*/}
                {/*        scale="point"*/}
                {/*        padding={{ left: 10, right: 10 }}*/}
                {/*    />*/}
                {/*    <YAxis />*/}
                {/*    <Tooltip />*/}
                {/*    <Legend />*/}
                {/*    <CartesianGrid strokeDasharray="3 3" />*/}
                {/*    <Bar dataKey="value" fill="#8884d8" background={{ fill: "#eee" }} />*/}
                {/*</BarChart>*/}
            </div>
        </div>
    );
}
