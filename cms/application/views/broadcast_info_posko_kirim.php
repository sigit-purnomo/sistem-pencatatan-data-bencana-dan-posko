<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
      <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Pengelolaan Pesan - Broadcast Info Bencana</title>
    <!-- BOOTSTRAP STYLES-->
    <link href="<?php echo base_url();?>assets/css/bootstrap.css" rel="stylesheet" />
     <!-- FONTAWESOME STYLES-->
    <link href="<?php echo base_url();?>assets/css/font-awesome.css" rel="stylesheet" />
     <!-- MORRIS CHART STYLES-->
   
        <!-- CUSTOM STYLES-->
    <link href="<?php echo base_url();?>assets/css/custom.css" rel="stylesheet" />
     <!-- GOOGLE FONTS-->
   <!-- <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' /> -->
   <link href="<?php echo base_url();?>assets/css/fonts.css" rel="stylesheet" />
     <!-- TABLE STYLES-->
    <link href="<?php echo base_url();?>assets/js/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
</head>
<body>
    <div id="wrapper">
        <nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html">UAJY - BPBD</a> 
            </div>
  <div style="color: white;
padding: 15px 50px 5px 50px;
float: right;
font-size: 16px;"> Selamat Datang <?php echo $username; ?> &nbsp; <a href="<?php echo site_url('login_control/logout');?>" class="btn btn-danger square-btn-adjust">Keluar</a> </div>
        </nav>   
           <!-- /. NAV TOP  -->
                <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="main-menu">
                <li class="text-center">
                    <img src="<?php echo base_url('assets/img/find_user.png');?>" class="user-image img-responsive"/>
                    </li>
                
                    
                    <!-- <li>
                        <a href="<?php echo site_url('home_control');?>"><i class="fa fa-home fa-3x"></i> Halaman Utama</a>
                    </li> -->
                    <!-- <li>
                        <a href="<?php echo site_url('petugas_control');?>"><i class="fa fa-users fa-3x"></i> Pengelolaan Petugas<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="<?php echo site_url('petugas_control');?>">Tampil Data</a>
                            </li>
                            <li>
                                <a href="<?php echo site_url('petugas_control/view_tambah_petugas');?>">Tambah Data</a>
                            </li>
                            <li>
                                <a href="<?php echo site_url('petugas_control/view_ubah_petugas');?>">Ubah Data</a>
                            </li>
                            <li>
                                <a href="<?php echo site_url('petugas_control/view_hapus_petugas');?>">Hapus Data</a>
                            </li>
                        </ul>
                    </li>   --> 
                    <li>
                        <a class="active-menu" href="#"><i class="fa fa-envelope-o fa-3x"></i> Pengelolaan Pesan<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="<?php echo site_url('pesan_control');?>">Pesan Masuk</a>
                            </li>
                            <li>
                                <a href="<?php echo site_url('pesan_control/view_pesan_terkirim');?>">Pesan Terkirim</a>
                            </li>
                            <li>
                                <a href="<?php echo site_url('pesan_control/view_tulis_pesan');?>">Tulis Pesan</a>
                            </li>
                            <li>
                                <a href="<?php echo site_url('pesan_control/view_broadcast_bencana');?>">Broadcast Informasi Bencana</a>
                            </li>
                            <li>
                                <a class="active-sub-menu" href="<?php echo site_url('pesan_control/view_broadcast_posko');?>">Broadcast Informasi Posko</a>
                            </li>
                        </ul>
                    </li>  
                </ul>
               
            </div>
            
        </nav>  
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper" >
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                     <h2>Pengelolaan Pesan - Broadcast Info Posko</h2>    
                     
                        <!-- <h5>Welcome Jhon Deo , Love to see you back. </h5> -->
                       
                    </div>
                </div>
                 <!-- /. ROW  -->
                 <hr />
            
            <div class="row">
                <div class="col-md-12">
                    <!-- Advanced Tables -->
                    <?php echo form_open("pesan_control/broadcast_posko"); ?>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                             <!-- Advanced Tables -->
                             Data Petugas Dipilih
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="petugas">
                                    <thead>
                                        <tr>
                                            <th>No</th>
                                            <th>ID Petugas</th>
                                            <th>Nama Petugas</th>
                                            <th>Username</th>
                                            <th>Nomor Telepon</th>
                                            <th>Role</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        
                                        <?php
                                        $i = 0;
                                        if(!empty($petugas_dipilih))
                                        {
                                            foreach ($petugas_dipilih as $rows2) { 
                                                $i++;
                                            ?>
                                            <tr>
                                                <td><?php echo $i; ?></td>
                                                <td><?php echo $rows2[0]->id_user; ?></td>
                                                <td><?php echo $rows2[0]->nama_lengkap; ?></td>
                                                <td><?php echo $rows2[0]->username; ?></td>
                                                <td><?php echo $rows2[0]->no_hp; ?></td>
                                                <td>
                                                <?php if($rows2[0]->id_role == 1)
                                                {
                                                    echo "Petugas";
                                                }
                                                else if($rows2[0]->id_role == 2)
                                                {
                                                    echo "Relawan";
                                                }
                                                ?>
                                                </td>
                                            </tr>   
                                            <?php
                                            }
                                        }
                                        ?>
                                    </tbody>
                                </table>
                            </div>
                            
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                             <!-- Advanced Tables -->
                             Data Posko Dipilih
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="posko">
                                    <thead>
                                        <tr>
                                            <th>No</th>
                                            <th>ID Posko</th>
                                            <th>ID Bencana</th>
                                            <th>Nama Posko</th>
                                            <th>Latitude</th>
                                            <th>Longitude</th>
                                            <th>Dusun</th>
                                            <th>Kecamatan</th>
                                            <th>Kota</th>
                                            <th>Provinsi</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        
                                        <?php
                                        $i = 0;
                                        if(!empty($posko_dipilih))
                                        {
                                            foreach ($posko_dipilih as $rows) { 
                                                $i++;
                                            ?>
                                            <tr>
                                                <td><?php echo $i; ?></td>
                                                <td><?php echo $rows[0]->id_posko; ?></td>
                                                <td><?php echo $rows[0]->id_bencana; ?></td>
                                                <td><?php echo $rows[0]->nama_posko; ?></td>
                                                <td><?php echo $rows[0]->latitude; ?></td>
                                                <td><?php echo $rows[0]->longitude; ?></td>
                                                <td><?php echo $rows[0]->lokasi_posko_dusun; ?></td>
                                                <td><?php echo $rows[0]->lokasi_posko_kecamatan; ?></td>
                                                <td><?php echo $rows[0]->lokasi_posko_kota; ?></td>
                                                <td><?php echo $rows[0]->lokasi_posko_provinsi; ?></td>
                                            </tr>   
                                            <?php
                                            }
                                        }
                                        ?>
                                    </tbody>
                                </table>
                            </div>
                            
                        </div>
                    </div>
                    <!--End Advanced Tables -->
                    <input type="button" class="btn btn-primary" value="Batal" onclick="return checkBatalBroadcastPosko()"/>
                    <input type="submit" class="btn btn-default" value="Broadcast Data" onclick="return checkBroadcastPosko()"/>
                    
                    <?php echo form_close(); ?>
                </div>
            </div>
        </div>      
    </div>
             <!-- /. PAGE INNER  -->
            </div>
         <!-- /. PAGE WRAPPER  -->
     <!-- /. WRAPPER  -->
    <!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
    <!-- JQUERY SCRIPTS -->
    <script src="<?php echo base_url();?>assets/js/jquery-1.10.2.js"></script>
      <!-- BOOTSTRAP SCRIPTS -->
    <script src="<?php echo base_url();?>assets/js/bootstrap.min.js"></script>
    <!-- METISMENU SCRIPTS -->
    <script src="<?php echo base_url();?>assets/js/jquery.metisMenu.js"></script>
     <!-- DATA TABLE SCRIPTS -->
    <script src="<?php echo base_url();?>assets/js/dataTables/jquery.dataTables.js"></script>
    <script src="<?php echo base_url();?>assets/js/dataTables/dataTables.bootstrap.js"></script>
        <script>
            $(document).ready(function () {
                $('#dataTables-example').dataTable();
            });
    </script>
         <!-- CUSTOM SCRIPTS -->
    <script src="<?php echo base_url();?>assets/js/custom.js"></script>
    <script language="JavaScript" type="text/javascript">
            function checkEdit(){
                return confirm('Anda yakin mengubah data petugas ini?');
            }
    </script>
    <script language="JavaScript" type="text/javascript">
        function checkDelete(){
            return confirm('Anda yakin menghapus data petugas ini?');
        }
    </script>
    <script language="JavaScript" type="text/javascript">
        function checkBroadcastPosko(){
            return confirm('Anda yakin broadcast data posko ini?');
        }
    </script>
    <script language="JavaScript" type="text/javascript">
        function checkBatalBroadcastBencana(){
            // return confirm('Anda yakin membatalkan broadcast data bencana ini?');
            var r = confirm("Anda yakin membatalkan broadcast data posko ini?");
            if (r == true) {
                // txt = "You pressed OK!";
                window.location.href = "<?php echo site_url('pesan_control/view_broadcast_posko');?>";
            } 
        }
    </script>

</body>
</html>
