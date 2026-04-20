import { useState } from 'react';
import SistemaDoctores from './components/SistemaDoctores';
import SistemaVisualizacion from './components/SistemaEstado';
import './App.css';

function App() {
  const [vistaActual, setVistaActual] = useState('doctores');

  return (
    <div style={{ fontFamily: 'sans-serif', maxWidth: '800px', margin: '0 auto', padding: '20px' }}>
      
      {/* Menú de navegación */}
      <div style={{ display: 'flex', justifyContent: 'center', gap: '20px', marginBottom: '30px' }}>
        <button 
            onClick={() => setVistaActual('doctores')}
            style={{ fontWeight: vistaActual === 'doctores' ? 'bold' : 'normal', border: '2px solid #4CAF50' }}
        >
            Abrir App Doctores
        </button>
        <button 
            onClick={() => setVistaActual('visualizacion')}
            style={{ fontWeight: vistaActual === 'visualizacion' ? 'bold' : 'normal', border: '2px solid #17a2b8' }}
        >
            Abrir App Visualización
        </button>
      </div>

      {/* Renderizado condicional de las aplicaciones */}
      {vistaActual === 'doctores' ? <SistemaDoctores /> : <SistemaVisualizacion />}
      
    </div>
  );
}

export default App;