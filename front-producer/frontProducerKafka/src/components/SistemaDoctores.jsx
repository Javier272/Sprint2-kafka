import { useState } from "react";

export default function SistemaDoctores() {
    const [paciente, setPaciente] = useState({ id: "", nombre: "", accion: "0" });
    const [cita, setCita] = useState({ idPaciente: "", fecha: "", doctor: "", accion: "0" });

    // Función genérica para enviar a nuestro backend
    const publicarEvento = async (topic, partition, payload) => {
        try {
            await fetch(`http://localhost:8000/producer/${topic}/${partition}`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(payload)
            });
            alert(`Evento enviado a ${topic} (Partición ${partition})`);
        } catch (error) {
            console.error("Error enviando evento:", error);
        }
    };

    const manejarPaciente = (e) => {
        e.preventDefault();
        // accion "0" = Registro, "1" = Actualización, "2" = Eliminación
        publicarEvento("gestion-pacientes", parseInt(paciente.accion), paciente);
        setPaciente({ id: "", nombre: "", accion: "0" }); // Limpiar formulario
    };

    const manejarCita = (e) => {
        e.preventDefault();
        // accion "0" = Creación, "1" = Cancelación, "2" = Reprogramación
        publicarEvento("gestion-citas", parseInt(cita.accion), cita);
        setCita({ idPaciente: "", fecha: "", doctor: "", accion: "0" });
    };

    return (
        <div style={{ padding: '20px', border: '1px solid #ccc', borderRadius: '8px', marginBottom: '20px' }}>
            <h2>Sistema Doctores</h2>
            
            <div style={{ display: 'flex', gap: '40px', marginTop: '20px' }}>
                {/* FORMULARIO PACIENTES */}
                <form onSubmit={manejarPaciente} style={{ display: 'flex', flexDirection: 'column', gap: '10px', width: '300px' }}>
                    <h3>Gestión de Pacientes</h3>
                    <select value={paciente.accion} onChange={(e) => setPaciente({...paciente, accion: e.target.value})}>
                        <option value="0">Registrar Paciente</option>
                        <option value="1">Actualizar Paciente</option>
                        <option value="2">Eliminar Paciente</option>
                    </select>
                    <input type="text" placeholder="ID Paciente" value={paciente.id} onChange={(e) => setPaciente({...paciente, id: e.target.value})} required />
                    <input type="text" placeholder="Nombre (Ej: Javier)" value={paciente.nombre} onChange={(e) => setPaciente({...paciente, nombre: e.target.value})} />
                    <button type="submit" style={{ backgroundColor: '#4CAF50', color: 'white' }}>Enviar Evento de Paciente</button>
                </form>

                {/* FORMULARIO CITAS */}
                <form onSubmit={manejarCita} style={{ display: 'flex', flexDirection: 'column', gap: '10px', width: '300px' }}>
                    <h3>Agendamiento de Citas</h3>
                    <select value={cita.accion} onChange={(e) => setCita({...cita, accion: e.target.value})}>
                        <option value="0">Crear Cita</option>
                        <option value="1">Cancelar Cita</option>
                        <option value="2">Reprogramar Cita</option>
                    </select>
                    <input type="text" placeholder="ID Paciente" value={cita.idPaciente} onChange={(e) => setCita({...cita, idPaciente: e.target.value})} required />
                    <input type="datetime-local" value={cita.fecha} onChange={(e) => setCita({...cita, fecha: e.target.value})} required />
                    <button type="submit" style={{ backgroundColor: '#2196F3', color: 'white' }}>Enviar Evento de Cita</button>
                </form>
            </div>
        </div>
    );
}