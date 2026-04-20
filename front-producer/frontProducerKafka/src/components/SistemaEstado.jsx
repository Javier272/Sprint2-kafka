import { useState } from "react";

export default function SistemaVisualizacion() {
    const [consulta, setConsulta] = useState({ idPaciente: "", tipoConsulta: "0" });

    const solicitarVisualizacion = async (e) => {
        e.preventDefault();
        
        // tipoConsulta "0" = Estado, "1" = Historial médico
        const particion = parseInt(consulta.tipoConsulta);
        
        try {
            await fetch(`http://localhost:8000/producer/visualizacion/${particion}`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ idPaciente: consulta.idPaciente, timestamp: new Date().toISOString() })
            });
            alert(`Solicitud de visualización enviada (Partición ${particion})`);
        } catch (error) {
            console.error("Error enviando consulta:", error);
        }
    };

    return (
        <div style={{ padding: '20px', border: '1px solid #17a2b8', borderRadius: '8px' }}>
            <h2>Sistema de Estados</h2>
            <p>Solicita el estado actual o el historial médico de un paciente.</p>

            <form onSubmit={solicitarVisualizacion} style={{ display: 'flex', flexDirection: 'column', gap: '10px', width: '300px', marginTop: '20px' }}>
                <select value={consulta.tipoConsulta} onChange={(e) => setConsulta({...consulta, tipoConsulta: e.target.value})}>
                    <option value="0">Consultar Estado del Paciente</option>
                    <option value="1">Consultar Historial Médico</option>
                </select>
                <input 
                    type="text" 
                    placeholder="ID del Paciente a consultar" 
                    value={consulta.idPaciente} 
                    onChange={(e) => setConsulta({...consulta, idPaciente: e.target.value})} 
                    required 
                />
                <button type="submit" style={{ backgroundColor: '#17a2b8', color: 'white' }}>
                    Solicitar Datos
                </button>
            </form>
        </div>
    );
}