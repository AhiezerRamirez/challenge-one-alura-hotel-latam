package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.alura.hotel.controller.ReservationController;
import com.alura.hotel.modelo.Guest;
import com.alura.hotel.modelo.Reservation;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	
	private ReservationController reservationController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		this.reservationController = new ReservationController();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		
		
		
		tbReservas = new JTable(){
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return column==0 ? false : true;
		    }
		};
				
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(txtBuscar.getText().isBlank()) {
					vaciarTablaReservas();
					llenarTablaReservaciones();
					return;
				}
				
				if(panel.getSelectedIndex() == 0) {
					boolean isANumber = isNumeric(txtBuscar.getText());
					if(!isANumber) {
						JOptionPane.showMessageDialog(null, "Utilize el número de reserva para buscar");
						vaciarTablaReservas();
						llenarTablaReservaciones();
						return;
					}
					boolean isRowFound = buscarReserva(txtBuscar.getText());
					if(!isRowFound) {
						JOptionPane.showMessageDialog(null, "No hay coincidencias");
						llenarTablaReservaciones();
					}
				}else if(panel.getSelectedIndex() == 1) {
					boolean isRowFound = buscarHuesped(txtBuscar.getText());
					if(!isRowFound) {
						JOptionPane.showMessageDialog(null, "No hay coincidencias");
						llenarTablaHuespedes();
					}
				}
			}
		});
		
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		btnEditar.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(panel.getSelectedIndex() == 0) {
					int row = tbReservas.getSelectedRow();
					if(row >=0) {
						String idReservation = tbReservas.getModel().getValueAt(row, 0).toString();
						String fechaEntrada= tbReservas.getModel().getValueAt(row, 1).toString();
						String fechaSalida = tbReservas.getModel().getValueAt(row, 2).toString();
						String total = tbReservas.getModel().getValueAt(row, 3).toString();
						String metodoPago = tbReservas.getModel().getValueAt(row, 4).toString();
						editarReservacion(idReservation, fechaEntrada, fechaSalida, total, metodoPago);
						
						System.out.println("Seleccionó el ID: "+idReservation);
					}else {
						JOptionPane.showMessageDialog(null, "Seleccione y modifique la reseva");
					}
				}else if(panel.getSelectedIndex()==1) {
					int row = tbHuespedes.getSelectedRow();
					System.out.println(row);
					if(row >= 0) {
						String idHuesped=tbHuespedes.getModel().getValueAt(row, 0).toString();
						String nombre=tbHuespedes.getModel().getValueAt(row, 1).toString();
						String apellido=tbHuespedes.getModel().getValueAt(row, 2).toString();
						String nacimiento=tbHuespedes.getModel().getValueAt(row, 3).toString();
						String nacionalidad=tbHuespedes.getModel().getValueAt(row, 4).toString();
						String telefono=tbHuespedes.getModel().getValueAt(row, 5).toString();
						String idReserva=tbHuespedes.getModel().getValueAt(row, 6).toString();
						System.out.println(row + " Row " + telefono);
						reservationController.editGuest(idHuesped, nombre, apellido, nacimiento, nacionalidad, telefono, idReserva);
					}
				}
			}
		});
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(panel.getSelectedIndex() == 0) {
					int row = tbReservas.getSelectedRow();
					System.out.println("Row: "+ row);
					if(row >=0) {
						String idReservation = tbReservas.getModel().getValueAt(row, 0).toString();
						reservationController.deleteReservation(idReservation);
						modelo.removeRow(row);
					}else {
						JOptionPane.showMessageDialog(null, "Seleccione una reserva a eliminar");
					}
				}else if(panel.getSelectedIndex() == 1) {
					int row = tbHuespedes.getSelectedRow();
					if(row >= 0) {
						String idGuest= modeloHuesped.getValueAt(row, 0).toString();
						reservationController.deleteGuest(idGuest);
						modeloHuesped.removeRow(row);
					}
				}
			}
		});
		
		llenarTablaReservaciones();
		llenarTablaHuespedes();
	}
	
	public void llenarTablaReservaciones() {
		List<Reservation> reservations = this.reservationController.getReservationList();
		for (Reservation reservation : reservations) {
			this.modelo.addRow(new Object[] {
					reservation.getId(),
					reservation.getCheckInDate(),
					reservation.getCheckOutDate(),
					reservation.getTotal(),
					reservation.getPaymentMethod()
			});
		}
	}
	
	public void llenarTablaHuespedes() {
		List<Guest> guest = this.reservationController.getGuestList();
		for (Guest guest2 : guest) {
			this.modeloHuesped.addRow(new Object[] {
					guest2.getId(),
					guest2.getFirstName(),
					guest2.getLastName(),
					guest2.getDetaOfBirth(),
					guest2.getNationality(),
					guest2.getPhoneNumber(),
					guest2.getReservationId(),
			});
		}
	}
	
	private void editarReservacion(String idReservation, String fechaEntrada, 
			String fechaSalida, String total, String metodoPago) {
		this.reservationController.editReservation(idReservation, fechaEntrada, fechaSalida, total, metodoPago);
	}
	
	private void vaciarTablaReservas() {
		int rows = this.modelo.getRowCount()-1;
		for(int i = rows; i >= 0; i--) {
			this.modelo.removeRow(i);
		}
	}
	
	private boolean buscarReserva(String parametro) {
		int filas = tbReservas.getRowCount()-1;
		Object[] filaCorrecta=null;
		for (int i = filas; i >= 0 ; i--) {
			System.out.println(tbReservas.getModel().getValueAt(i, 0).toString());
			if(tbReservas.getModel().getValueAt(i, 0).toString().equals(parametro)) {
				filaCorrecta = new Object[] {
						tbReservas.getModel().getValueAt(i, 0).toString(),
						tbReservas.getModel().getValueAt(i, 1).toString(),
						tbReservas.getModel().getValueAt(i, 2).toString(),
						tbReservas.getModel().getValueAt(i, 3).toString(),
						tbReservas.getModel().getValueAt(i, 4).toString(),
				};
				System.out.println(filaCorrecta);
			}
			this.modelo.removeRow(i);
		}
		if(filaCorrecta != null) {
			this.modelo.addRow(filaCorrecta);
			return true;
		}
		return false;
	}
	
	private boolean buscarHuesped(String parametro) {
		int filas = tbHuespedes.getModel().getRowCount()-1;
		Object[] filaCorrecta=null;
		for(int i = filas; i >= 0 ; i--) {
			if(modeloHuesped.getValueAt(i, 2).toString().equals(parametro)) {
				filaCorrecta = new Object[] {
						modeloHuesped.getValueAt(i, 0).toString(),
						modeloHuesped.getValueAt(i, 1).toString(),
						modeloHuesped.getValueAt(i, 2).toString(),
						modeloHuesped.getValueAt(i, 3).toString(),
						modeloHuesped.getValueAt(i, 4).toString(),
						modeloHuesped.getValueAt(i, 5).toString(),
						modeloHuesped.getValueAt(i, 6).toString()
				};
			}
			modeloHuesped.removeRow(i);
		}
		if(filaCorrecta !=null) {
			this.modeloHuesped.addRow(filaCorrecta);
			return true;
		}
		return false;
	}
	
//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
	    }
	    
	    public  boolean isNumeric(String strNum) {
	        if (strNum == null) {
	            return false;
	        }
	        try {
	            int d = Integer.parseInt(strNum);
	        } catch (NumberFormatException nfe) {
	            return false;
	        }
	        return true;
	    }
}
